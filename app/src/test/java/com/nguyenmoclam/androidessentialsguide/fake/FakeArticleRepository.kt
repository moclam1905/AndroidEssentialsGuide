package com.nguyenmoclam.androidessentialsguide.fake

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FakeArticleRepository : ArticleRepository {
    private var fetchArticleCallCount = 0
    private lateinit var articleListContinuation: Continuation<List<Article>>

    override suspend fun fetchArticles(): List<Article> {
        fetchArticleCallCount++
        return suspendCoroutine { continuation ->
            articleListContinuation = continuation
        }
    }

    fun emitArticles(articles: List<Article>) {
        articleListContinuation.resume(articles)
    }

    fun getFetchArticleCallCount() = fetchArticleCallCount
}
