package com.nguyenmoclam.androidessentialsguide.data.local

import kotlinx.coroutines.flow.Flow

interface ArticleDatabase {
    fun fetchBookmarks(): Flow<List<PersistableArticle>>

    suspend fun insertArticle(article: PersistableArticle)
}
