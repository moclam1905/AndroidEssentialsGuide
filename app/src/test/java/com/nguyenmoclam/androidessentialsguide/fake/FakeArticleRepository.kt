package com.nguyenmoclam.androidessentialsguide.fake

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

class FakeArticleRepository : ArticleRepository {
    private var fetchArticleCallCount = 0
    private val persistArticles: MutableList<Article> = mutableListOf()
    private val articleListChannel: Channel<DataResponse<List<Article>>> = Channel()

    override fun fetchArticles(): Flow<DataResponse<List<Article>>> {
        fetchArticleCallCount++
        return articleListChannel.consumeAsFlow()
    }

    override suspend fun persistArticle(article: Article) {
        persistArticles.add(article)
    }

    suspend fun emitArticles(articles: List<Article>) {
        val response = DataResponse.Success(articles)
        articleListChannel.send(response)
    }

    suspend fun emitFailure(error: Throwable) {
        val response = DataResponse.Error(error)
        articleListChannel.send(response)
    }

    fun getFetchArticleCallCount() = fetchArticleCallCount

    fun getPersistArticles(): List<Article> {
        return persistArticles.toList()
    }

    fun closeChannels() {
        articleListChannel.close()
    }
}
