package com.nguyenmoclam.androidessentialsguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.nguyenmoclam.androidessentialsguide.articlelist.ArticleListViewState
import com.nguyenmoclam.androidessentialsguide.articlelist.BaseArticleListViewModel
import com.nguyenmoclam.androidessentialsguide.compose.ArticleList
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
    Box(
        modifier =
            modifier
                .fillMaxSize()
                .padding(paddingValue),
    ) {
        when (val currentState = state.value) {
            is ArticleListViewState.Loading -> {
                Box(
                    modifier = modifier.fillMaxSize(),
                ) {
                    CircularProgressIndicator(
                        modifier = modifier.wrapContentSize().align(Alignment.Center),
                    )
                }
            }

            is ArticleListViewState.Success -> {
                ArticleList(
                    articles = currentState.articles,
                    onBookmarkClick = { article -> onBookmarkClick(article) },
                    onArticleClick = { article -> onArticleClick(article) },
                )
            }

            ArticleListViewState.Empty -> {}
            is ArticleListViewState.Error -> {
            }

            null -> {}
        }
    }
}
