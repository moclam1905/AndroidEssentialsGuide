package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

@Composable
fun ArticleGrid(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    val numberOfColumns = 3

    LazyVerticalGrid(
        columns = GridCells.Fixed(numberOfColumns),
        modifier = modifier.fillMaxSize(),
    ) {
        items(articles) { article ->
            Box(
                modifier = modifier.padding(dimensionResource(id = R.dimen.article_list_padding)),
            ) {
                ArticleListItem(
                    article = article,
                    onBookmarkClick = onBookmarkClick,
                    onArticleClick = onArticleClick,
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun ArticleGridPreview(modifier: Modifier = Modifier) {
    val firstArticle =
        Article(
            htmlTitle = HtmlString("Jetpack Compose"),
            authorName = "Moclam",
            tags = listOf("Jetpack", "Compose"),
            bookmark = true,
        )

    val secondArticle = firstArticle.copy(bookmark = false)
    val thirdArticle = secondArticle.copy(tags = listOf("Android", "Kotlin"))

    val articles = listOf(firstArticle, secondArticle, thirdArticle)

    StudyGuideTheme {
        ArticleGrid(
            articles = articles,
            onBookmarkClick = {},
            onArticleClick = {},
        )
    }
}
