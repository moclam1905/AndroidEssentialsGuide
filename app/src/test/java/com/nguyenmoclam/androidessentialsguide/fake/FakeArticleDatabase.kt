package com.nguyenmoclam.androidessentialsguide.fake

import com.nguyenmoclam.androidessentialsguide.data.local.ArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.PersistableArticle

class FakeArticleDatabase : ArticleDatabase {
    private var mockedBookmarks = listOf<PersistableArticle>()
    private var insertArticleCallCount: Int = 0

    override suspend fun fetchBookmarks(): List<PersistableArticle> {
        return mockedBookmarks
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
