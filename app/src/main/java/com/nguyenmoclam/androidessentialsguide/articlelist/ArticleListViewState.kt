package com.nguyenmoclam.androidessentialsguide.articlelist

import com.nguyenmoclam.androidessentialsguide.models.Article

sealed class ArticleListViewState {
    data object Loading : ArticleListViewState()

    data class Success(val articles: List<Article>) : ArticleListViewState()

    data class Error(val message: Throwable) : ArticleListViewState()
}
