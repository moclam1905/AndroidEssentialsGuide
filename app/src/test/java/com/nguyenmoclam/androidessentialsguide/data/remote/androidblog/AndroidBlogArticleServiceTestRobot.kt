package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.data.local.PersistableArticle
import com.nguyenmoclam.androidessentialsguide.fake.FakeArticleDatabase
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.flow.single

class AndroidBlogArticleServiceTestRobot {
    private val fakeApi = FakeAndroidBlogRetrofitAPI()
    private val fakeDatabase = FakeArticleDatabase()
    private lateinit var service: AndroidBlogArticleService

    fun buildService() =
        apply {
            this.service = AndroidBlogArticleService(fakeApi, fakeDatabase)
        }

    fun mockFeed(feed: AndroidBlogFeed) =
        apply {
            fakeApi.setMockedFeed(feed)
        }

    suspend fun assertArticles(expectedArticles: List<Article>) =
        apply {
            val response = service.fetchArticles().single()
            val actualArticles = (response as DataResponse.Success<List<Article>>).data

            assertThat(actualArticles).isEqualTo(expectedArticles)
        }

    fun mockBookmarks(bookmarks: List<PersistableArticle>) =
        apply {
            fakeDatabase.setMockedBookmarks(bookmarks)
        }

    suspend fun persistArticle(article: Article) =
        apply {
            service.persistArticle(article)
        }

    fun assertInsertArticleToDBCallCount(expectedCount: Int) =
        apply {
            assertThat(fakeDatabase.getInsertArticleCallCount()).isEqualTo(expectedCount)
        }
}
