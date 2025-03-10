package com.nguyenmoclam.androidessentialsguide.di

import android.content.Context
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsTracker
import com.nguyenmoclam.androidessentialsguide.analytics.SegmentAnalyticsTracker
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.BookmarkedArticleService
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogArticleService
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogRetrofitAPI
import com.segment.analytics.Analytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object DataModule {
    @AndroidBlogArticles
    @Provides
    fun provideAndroidBlogArticles(
        api: AndroidBlogRetrofitAPI,
        database: ArticleDatabase,
    ): ArticleRepository {
        return AndroidBlogArticleService(api, database)
    }

    @BookmarkedArticles
    @Provides
    fun provideBookmarkedArticles(database: ArticleDatabase): ArticleRepository {
        return BookmarkedArticleService(database)
    }

    @Provides
    fun provideAnalyticsTracker(
        @ApplicationContext context: Context,
    ): AnalyticsTracker {
        val segmentInstance = Analytics.with(context)

        return SegmentAnalyticsTracker(segmentInstance)
    }
}
