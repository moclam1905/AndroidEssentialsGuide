package com.nguyenmoclam.androidessentialsguide.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {
    @Query("SELECT * FROM PersistableArticle WHERE bookmarked = 1 ORDER BY publishedDate DESC")
    fun fetchBookmarks(): Flow<List<PersistableArticle>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticle(article: PersistableArticle)
}
