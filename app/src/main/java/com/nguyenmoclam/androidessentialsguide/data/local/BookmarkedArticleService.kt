package com.nguyenmoclam.androidessentialsguide.data.local

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class BookmarkedArticleService(
    private val database: ArticleDatabase,
) : ArticleRepository {
    override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
        return database.fetchBookmarks().map { persistableArticles ->
            val articles = persistableArticles.map(PersistableArticle::toArticle)

            DataResponse.Success(articles)
        }.catch { error ->
            DataResponse.Error(error)
        }
    }

    override suspend fun persistArticle(article: Article) {
        database.insertArticle(article.toPersistableArticle())
    }
}
