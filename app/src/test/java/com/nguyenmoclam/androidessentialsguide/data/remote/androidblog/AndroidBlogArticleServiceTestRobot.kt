package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
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
            val response = service.fetchArticles()
            val actualArticles = (response as DataResponse.Success<List<Article>>).data

            assertThat(actualArticles).isEqualTo(expectedArticles)
        }
}
