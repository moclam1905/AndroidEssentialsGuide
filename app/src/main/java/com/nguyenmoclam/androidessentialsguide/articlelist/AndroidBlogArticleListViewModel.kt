package com.nguyenmoclam.androidessentialsguide.articlelist

import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsTracker
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.di.AndroidBlogArticles
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AndroidBlogArticleListViewModel
    @Inject
    constructor(
        @AndroidBlogArticles
        private val articleRepository: ArticleRepository,
        analyticsTracker: AnalyticsTracker,
    ) : BaseArticleListViewModel(articleRepository, analyticsTracker) {
        override val emptyStateMessageTextRes: Int
            get() = R.string.android_blog_empty_message
    }
