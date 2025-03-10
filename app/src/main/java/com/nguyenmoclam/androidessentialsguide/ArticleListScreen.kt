package com.nguyenmoclam.androidessentialsguide

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.nguyenmoclam.androidessentialsguide.articlelist.BaseArticleListViewModel
import com.nguyenmoclam.androidessentialsguide.models.Article

@Composable
fun ArticleListScreen(
    modifier: Modifier = Modifier,
    viewModel: BaseArticleListViewModel,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
    paddingValue: PaddingValues,
) {
    val state = viewModel.state.observeAsState()
    ArticleListScreenContent(
        currentState = state.value,
        onBookmarkClick = onBookmarkClick,
        onArticleClick = onArticleClick,
        modifier =
            modifier
                .padding(paddingValue)
                .fillMaxSize(),
    )
}
