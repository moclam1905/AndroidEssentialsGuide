package com.nguyenmoclam.androidessentialsguide.articlelist

import android.content.res.Resources
import android.text.Spanned
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article

class ArticleListItemViewModel {
    var article: Article? = null

    val bookmarkButtonRes: Int
        get() =
            if (article?.bookmark == true) {
                R.drawable.ic_bookmark_selected
            } else {
                R.drawable.ic_bookmark_unselected
            }

    val articleTitle: Spanned?
        get() = article?.htmlTitle?.getSpannedString()

    fun getAuthorText(resource: Resources): String {
        return resource.getString(R.string.by_author, article?.authorName)
    }

    val articleTags: List<String>
        get() = article?.tags.orEmpty()
}
