package com.nguyenmoclam.androidessentialsguide.data

import com.nguyenmoclam.androidessentialsguide.models.Article

interface ArticleRepository {
    fun fetchArticles(): List<Article>
}
