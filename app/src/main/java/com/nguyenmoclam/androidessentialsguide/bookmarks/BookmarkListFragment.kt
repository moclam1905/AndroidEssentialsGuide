package com.nguyenmoclam.androidessentialsguide.bookmarks

import androidx.fragment.app.viewModels
import com.nguyenmoclam.androidessentialsguide.articlelist.BaseArticleListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkListFragment : BaseArticleListFragment() {
    override val viewModel: BookmarkListViewModel by viewModels()
}
