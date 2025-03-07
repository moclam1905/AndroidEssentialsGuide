package com.nguyenmoclam.androidessentialsguide.di

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogArticleService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindArticleRepository(androidBlogArticleService: AndroidBlogArticleService): ArticleRepository
}
