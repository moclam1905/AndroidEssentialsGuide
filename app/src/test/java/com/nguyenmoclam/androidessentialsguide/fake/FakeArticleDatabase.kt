package com.nguyenmoclam.androidessentialsguide.fake

import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.PersistableArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeArticleDatabase : ArticleDatabase {
    private var mockedBookmarks = listOf<PersistableArticle>()
    private var insertArticleCallCount: Int = 0

    override fun fetchBookmarks(): Flow<List<PersistableArticle>> {
        return flowOf(mockedBookmarks)
    }

    override suspend fun insertArticle(article: PersistableArticle) {
        insertArticleCallCount++
    }

    fun setMockedBookmarks(bookmarks: List<PersistableArticle>) {
        mockedBookmarks = bookmarks
    }

    fun getInsertArticleCallCount(): Int {
        return insertArticleCallCount
    }
}
