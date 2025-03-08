package com.nguyenmoclam.androidessentialsguide.data

import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun fetchArticles(): Flow<DataResponse<List<Article>>>

    suspend fun persistArticle(article: Article)
}
