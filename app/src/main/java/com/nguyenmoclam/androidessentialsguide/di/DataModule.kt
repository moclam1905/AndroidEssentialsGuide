package com.nguyenmoclam.androidessentialsguide.di

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.BookmarkedArticleService
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogArticleService
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

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
}
