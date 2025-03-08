package com.nguyenmoclam.androidessentialsguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nguyenmoclam.androidessentialsguide.models.Article

@Entity
data class PersistableArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String = "",
    val authorName: String = "",
    val bookmarked: Boolean = false,
)

fun Article.toPersistableArticle(): PersistableArticle {
    return PersistableArticle(
        url = url,
        title = htmlTitle.getInput(),
        authorName = authorName,
        bookmarked = bookmark,
    )
}
