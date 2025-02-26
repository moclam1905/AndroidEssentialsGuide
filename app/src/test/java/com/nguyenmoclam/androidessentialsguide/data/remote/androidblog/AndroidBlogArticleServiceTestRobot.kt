package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.models.Article

class AndroidBlogArticleServiceTestRobot {
    private val fakeApi = FakeAndroidBlogRetrofitAPI()
    private lateinit var service: AndroidBlogArticleService

    fun buildService() =
        apply {
            this.service = AndroidBlogArticleService(fakeApi)
        }

    fun mockFeed(feed: AndroidBlogFeed) =
        apply {
            fakeApi.setMockedFeed(feed)
        }

    suspend fun assertArticles(expectedArticles: List<Article>) =
        apply {
            val actualArticles = service.fetchArticles()
            assertThat(actualArticles).isEqualTo(expectedArticles)
        }
}
