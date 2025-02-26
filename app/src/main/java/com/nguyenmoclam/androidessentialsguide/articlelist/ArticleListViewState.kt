package com.nguyenmoclam.androidessentialsguide.articlelist

import com.nguyenmoclam.androidessentialsguide.models.Article

sealed class ArticleListViewState {
    data object Loading : ArticleListViewState()

    data class Success(val articles: List<Article>) : ArticleListViewState()

    class Error(val message: String) : ArticleListViewState()
}
