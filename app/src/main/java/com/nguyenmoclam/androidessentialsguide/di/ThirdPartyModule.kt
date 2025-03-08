package com.nguyenmoclam.androidessentialsguide.di

import android.content.Context
import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.RoomArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.RoomStudyGuideDatabase
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ThirdPartyModule {
    @Provides
    fun provideAndroidBlogRetrofitAPI(): AndroidBlogRetrofitAPI {
        return AndroidBlogRetrofitAPI.getDefaultApi()
    }

    @Provides
    fun provideArticleDatabase(
        @ApplicationContext appContext: Context,
    ): ArticleDatabase {
        val roomDatabase = RoomStudyGuideDatabase.createDatabase(appContext)
        return RoomArticleDatabase(roomDatabase)
    }
}
