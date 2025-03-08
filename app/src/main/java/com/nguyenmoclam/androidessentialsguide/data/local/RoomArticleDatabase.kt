package com.nguyenmoclam.androidessentialsguide.data.local

class RoomArticleDatabase(private val roomDatabase: RoomStudyGuideDatabase) : ArticleDatabase {
    override suspend fun fetchBookmarks(): List<PersistableArticle> {
        return roomDatabase.articleDao().fetchBookmarks()
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        roomDatabase.articleDao().insertArticle(article)
    }
}
