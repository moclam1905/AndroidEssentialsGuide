package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

@Composable
fun ArticleList(
    articles: List<Article>,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.article_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_list_spacing)),
    ) {
        items(articles) { article ->
            ArticleListItem(
                article = article,
                onBookmarkClick = onBookmarkClick,
                onArticleClick = onArticleClick,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewArticleList(modifier: Modifier = Modifier) {
    val firstArticle =
        Article(
            htmlTitle = HtmlString("Jetpack Compose"),
            authorName = "Moclam",
            tags = listOf("Jetpack", "Compose"),
            bookmark = true,
        )

    val secondArticle =
        firstArticle.copy(
            bookmark = false,
        )

    val thirdArticle =
        secondArticle.copy(
            tags = listOf("Android", "Kotlin"),
        )

    val articles = listOf(firstArticle, secondArticle, thirdArticle)

    StudyGuideTheme {
        ArticleList(
            articles = articles,
            onBookmarkClick = {},
            onArticleClick = {},
        )
    }
}
