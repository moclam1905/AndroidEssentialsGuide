package com.nguyenmoclam.androidessentialsguide.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [PersistableArticle::class],
    version = 1,
)
@TypeConverters(StringListConverter::class)
abstract class RoomStudyGuideDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDAO

    companion object {
        fun createDatabase(appContext: Context): RoomStudyGuideDatabase {
            return Room.databaseBuilder(
                appContext,
                RoomStudyGuideDatabase::class.java,
                "study_guide_database.db",
            ).build()
        }
    }
}
