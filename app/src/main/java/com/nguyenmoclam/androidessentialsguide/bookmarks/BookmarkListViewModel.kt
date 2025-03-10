package com.nguyenmoclam.androidessentialsguide.bookmarks

import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsTracker
import com.nguyenmoclam.androidessentialsguide.articlelist.BaseArticleListViewModel
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.di.BookmarkedArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkListViewModel
    @Inject
    constructor(
        @BookmarkedArticles
        private val articleRepository: ArticleRepository,
        analyticsTracker: AnalyticsTracker,
    ) : BaseArticleListViewModel(articleRepository, analyticsTracker) {
        override val emptyStateMessageTextRes: Int
            get() = R.string.bookmarks_empty_message
    }
