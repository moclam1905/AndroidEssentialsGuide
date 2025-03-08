package com.nguyenmoclam.androidessentialsguide

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nguyenmoclam.androidessentialsguide.data.local.PersistableArticle
import com.nguyenmoclam.androidessentialsguide.data.local.RoomArticleDatabase
import com.nguyenmoclam.androidessentialsguide.data.local.RoomStudyGuideDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomArticleDatabaseTest {
    private lateinit var appDatabase: RoomStudyGuideDatabase
    private lateinit var articleDatabase: RoomArticleDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(context, RoomStudyGuideDatabase::class.java).build()
        articleDatabase = RoomArticleDatabase(appDatabase)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    fun insertReadBookmark()  {
        val testArticle =
            PersistableArticle(
                url = "https://testUrl",
                title = "",
                authorName = "",
                bookmarked = true,
            )

        runBlocking {
            articleDatabase.insertArticle(testArticle)

            val bookmarks = articleDatabase.fetchBookmarks()
            assert(bookmarks.contains(testArticle))
        }
    }
}
