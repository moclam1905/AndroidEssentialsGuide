package com.nguyenmoclam.androidessentialsguide.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

@Entity
data class PersistableArticle(
    @PrimaryKey(autoGenerate = false)
    val url: String,
    val title: String = "",
    val authorName: String = "",
    val bookmarked: Boolean = false,
    val tags: List<String> = emptyList(),
    val publishedDate: String = "",
) {
    fun toArticle(): Article {
        return Article(
            htmlTitle = HtmlString(title),
            authorName = authorName,
            url = url,
            bookmark = bookmarked,
            tags = tags,
            publishedDate = publishedDate,
        )
    }
}

fun Article.toPersistableArticle(): PersistableArticle {
    return PersistableArticle(
        url = url,
        title = htmlTitle.getInput(),
        authorName = authorName,
        bookmarked = bookmark,
        tags = tags,
        publishedDate = publishedDate,
    )
}
