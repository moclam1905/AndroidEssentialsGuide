package com.nguyenmoclam.androidessentialsguide.data.local

import kotlinx.coroutines.flow.Flow

class RoomArticleDatabase(private val roomDatabase: RoomStudyGuideDatabase) : ArticleDatabase {
    override fun fetchBookmarks(): Flow<List<PersistableArticle>> {
        return roomDatabase.articleDao().fetchBookmarks()
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        roomDatabase.articleDao().insertArticle(article)
    }
}
