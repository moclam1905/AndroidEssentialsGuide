package com.nguyenmoclam.androidessentialsguide.data

import com.nguyenmoclam.androidessentialsguide.models.Article

interface ArticleRepository {
    suspend fun fetchArticles(): List<Article>
}
