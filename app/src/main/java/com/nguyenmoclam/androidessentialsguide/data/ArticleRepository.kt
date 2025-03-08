package com.nguyenmoclam.androidessentialsguide.data

import com.nguyenmoclam.androidessentialsguide.models.Article

interface ArticleRepository {
    suspend fun fetchArticles(): DataResponse<List<Article>>

    suspend fun persistArticle(article: Article)
}
