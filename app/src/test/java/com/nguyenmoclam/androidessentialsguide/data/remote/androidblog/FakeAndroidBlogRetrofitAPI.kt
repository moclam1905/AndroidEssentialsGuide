package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

class FakeAndroidBlogRetrofitAPI : AndroidBlogRetrofitAPI {
    private lateinit var mockedFeed: AndroidBlogFeed

    fun setMockedFeed(feed: AndroidBlogFeed) {
        this.mockedFeed = feed
    }

    override suspend fun getFeed(): AndroidBlogFeed {
        return mockedFeed
    }
}
