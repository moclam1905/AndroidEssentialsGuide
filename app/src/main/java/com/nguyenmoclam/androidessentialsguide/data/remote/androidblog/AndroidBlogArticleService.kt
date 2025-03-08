package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.toPersistableArticle
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AndroidBlogArticleService
    @Inject
    constructor(
        private val androidBlogRetrofitAPI: AndroidBlogRetrofitAPI,
        private val articleDatabase: ArticleDatabase,
    ) : ArticleRepository {
        override suspend fun fetchArticles(): DataResponse<List<Article>> =
            withContext(Dispatchers.IO) {
                return@withContext try {
                    val articleFetch =
                        async {
                            androidBlogRetrofitAPI.getFeed().items?.map(AndroidBlogFeedItem::toArticle)
                                .orEmpty()
                        }

                    val bookmarkFetch =
                        async {
                            articleDatabase.fetchBookmarks()
                        }

                    val articles = articleFetch.await()
                    val bookmarks = bookmarkFetch.await()

                    val updatedBookmarks =
                        articles.map { article ->
                            val isBookmarked = bookmarks.any { it.url == article.url }
                            article.copy(bookmark = isBookmarked)
                        }
                    DataResponse.Success(updatedBookmarks)
                } catch (e: Throwable) {
                    DataResponse.Error(e)
                }
            }

        override suspend fun persistArticle(article: Article) {
            articleDatabase.insertArticle(article.toPersistableArticle())
        }
    }

private fun AndroidBlogFeedItem.toArticle(): Article {
    return Article(
        htmlTitle = HtmlString(this.title.orEmpty()),
        authorName = this.author?.name.orEmpty(),
        url = this.link?.href.orEmpty(),
    )
}
