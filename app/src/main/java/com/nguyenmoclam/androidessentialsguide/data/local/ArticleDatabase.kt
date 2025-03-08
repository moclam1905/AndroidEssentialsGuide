package com.nguyenmoclam.androidessentialsguide.data.local

interface ArticleDatabase {
    suspend fun fetchBookmarks(): List<PersistableArticle>

    suspend fun insertArticle(article: PersistableArticle)
}
