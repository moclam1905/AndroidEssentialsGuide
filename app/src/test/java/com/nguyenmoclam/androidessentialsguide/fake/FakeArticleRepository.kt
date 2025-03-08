package com.nguyenmoclam.androidessentialsguide.fake

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeArticleRepository : ArticleRepository {
    private var fetchArticleCallCount = 0
    private val persistArticles: MutableList<Article> = mutableListOf()
    private lateinit var articleListContinuation: Continuation<DataResponse<List<Article>>>

    override suspend fun fetchArticles(): DataResponse<List<Article>> {
        fetchArticleCallCount++
        return suspendCoroutine { continuation ->
            articleListContinuation = continuation
        }
    }

    override suspend fun persistArticle(article: Article) {
        persistArticles.add(article)
    }

    fun emitArticles(articles: List<Article>) {
        val response = DataResponse.Success(articles)
        articleListContinuation.resume(response)
    }

    fun emitFailure(error: Throwable) {
        val response = DataResponse.Error<List<Article>>(error)
        articleListContinuation.resume(response)
    }

    fun getFetchArticleCallCount() = fetchArticleCallCount

    fun getPersistArticles(): List<Article> {
        return persistArticles.toList()
    }
}
