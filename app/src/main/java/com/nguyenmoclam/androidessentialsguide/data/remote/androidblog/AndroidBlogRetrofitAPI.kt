package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET

interface AndroidBlogRetrofitAPI {
    @GET("feed.xml")
    suspend fun getFeed(): AndroidBlogFeed

    companion object {
        private const val BASE_URL = "https://androidessence.com"

        fun getDefaultApi(): AndroidBlogRetrofitAPI {
            val loggingInterceptor =
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }

            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

            return Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).client(client).build()
                .create(AndroidBlogRetrofitAPI::class.java)
        }
    }
}
