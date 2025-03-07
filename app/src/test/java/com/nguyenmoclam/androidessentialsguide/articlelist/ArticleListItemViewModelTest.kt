package com.nguyenmoclam.androidessentialsguide.articlelist

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article
import org.junit.Test

class ArticleListItemViewModelTest {
    @Test
    fun getBookmarkIconRes() {
        val viewModel = ArticleListItemViewModel()

        viewModel.article = Article(bookmark = true)
        assertThat(viewModel.bookmarkButtonRes).isEqualTo(R.drawable.ic_bookmark_selected)

        viewModel.article = Article(bookmark = false)
        assertThat(viewModel.bookmarkButtonRes).isEqualTo(R.drawable.ic_bookmark_unselected)
    }
}
