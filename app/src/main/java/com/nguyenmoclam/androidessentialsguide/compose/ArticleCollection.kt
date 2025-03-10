package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.booleanResource
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article

@Composable
fun ArticleCollection(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    val shouldShowGrid = booleanResource(id = R.bool.showArticleGrid)

    if (shouldShowGrid) {
        ArticleGrid(
            modifier = modifier,
            articles = articles,
            onBookmarkClick = onBookmarkClick,
            onArticleClick = onArticleClick,
        )
    } else {
        ArticleList(
            articles = articles,
            onBookmarkClick = onBookmarkClick,
            onArticleClick = onArticleClick,
        )
    }
}
