package com.nguyenmoclam.androidessentialsguide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.fade
import com.google.accompanist.placeholder.material.placeholder
import com.nguyenmoclam.androidessentialsguide.articlelist.ArticleListViewState
import com.nguyenmoclam.androidessentialsguide.compose.ArticleCollection
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

@Composable
fun ArticleListScreenContent(
    modifier: Modifier = Modifier,
    currentState: ArticleListViewState?,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        when (currentState) {
            is ArticleListViewState.Success -> {
                ArticleCollection(
                    articles = currentState.articles,
                    onBookmarkClick = onBookmarkClick,
                    onArticleClick = onArticleClick,
                )
            }

            is ArticleListViewState.Loading -> {
                val placeholderArticles =
                    (1..5).map { index ->
                        Article(
                            htmlTitle = HtmlString("Placeholder Article $index"),
                            tags = listOf("testing", "testing"),
                        )
                    }
                ArticleCollection(
                    articles = placeholderArticles,
                    onBookmarkClick = onBookmarkClick,
                    onArticleClick = onArticleClick,
                    modifier = Modifier.progressSemantics(),
                    childModifier =
                        Modifier.placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.fade(),
                            shape = CircleShape,
                        ),
                )
            }

            ArticleListViewState.Empty -> {}
            is ArticleListViewState.Error -> {
            }
            else -> {}
        }
    }
}
