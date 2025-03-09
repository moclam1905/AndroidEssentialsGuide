package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.toPersistableArticle
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AndroidBlogArticleService
    @Inject
    constructor(
        private val androidBlogRetrofitAPI: AndroidBlogRetrofitAPI,
        private val articleDatabase: ArticleDatabase,
    ) : ArticleRepository {
        override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
            val apiArticleFlow =
                flow {
                    val article =
                        androidBlogRetrofitAPI.getFeed().items?.map(AndroidBlogFeedItem::toArticle)
                            .orEmpty()
                    emit(article)
                }

            val bookmarkedArticleFlow = articleDatabase.fetchBookmarks()

            return apiArticleFlow.combine(bookmarkedArticleFlow) { apiArticles, bookmarkedArticles ->
                val updatedBookmarks =
                    apiArticles.map { article ->
                        val isBookmarked = bookmarkedArticles.any { it.url == article.url }
                        article.copy(
                            bookmark = isBookmarked,
                        )
                    }
                DataResponse.Success(updatedBookmarks)
            }.catch {
                DataResponse.Error(it)
            }
        }

        override suspend fun persistArticle(article: Article) {
            articleDatabase.insertArticle(article.toPersistableArticle())
        }
    }

private fun AndroidBlogFeedItem.toArticle(): Article {
    val tags =
        this.categories?.mapNotNull { category ->
            category.term
        }.orEmpty()

    return Article(
        htmlTitle = HtmlString(this.title.orEmpty()),
        authorName = this.author?.name.orEmpty(),
        url = this.link?.href.orEmpty(),
        tags = tags,
        publishedDate = this.published.orEmpty(),
    )
}
