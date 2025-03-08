package com.nguyenmoclam.androidessentialsguide.di

import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogRetrofitAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ThirdPartyModule {
    @Provides
    fun provideAndroidBlogRetrofitAPI(): AndroidBlogRetrofitAPI {
        return AndroidBlogRetrofitAPI.getDefaultApi()
    }
}
