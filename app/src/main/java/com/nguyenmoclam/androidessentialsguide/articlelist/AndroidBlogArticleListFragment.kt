package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AndroidBlogArticleListFragment : BaseArticleListFragment() {
    override val viewModel: AndroidBlogArticleListViewModel by viewModels()
}
