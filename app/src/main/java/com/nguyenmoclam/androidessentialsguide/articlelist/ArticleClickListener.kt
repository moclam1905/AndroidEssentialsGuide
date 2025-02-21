package com.nguyenmoclam.androidessentialsguide.articlelist

import com.nguyenmoclam.androidessentialsguide.models.Article

interface ArticleClickListener {
    fun onArticleClicked(article: Article)
}
