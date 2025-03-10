package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices.PIXEL_C
import androidx.compose.ui.tooling.preview.Preview
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

val ColumnWidthKey = SemanticsPropertyKey<Float>("ColumnWidth")
var SemanticsPropertyReceiver.columnWidth by ColumnWidthKey

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    articles: List<Article>,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    val columnWidthPercentage = getColumnWidthPercentage()

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        ArticleListColumn(
            articles = articles,
            onBookmarkClick = onBookmarkClick,
            onArticleClick = onArticleClick,
            modifier =
                Modifier
                    .fillMaxSize()
                    .fillMaxWidth(columnWidthPercentage)
                    .align(Alignment.Center)
                    .semantics {
                        columnWidth = columnWidthPercentage
                    },
            childModifier = childModifier,
        )
    }
}

@Composable
private fun ArticleListColumn(
    modifier: Modifier = Modifier,
    childModifier: Modifier,
    articles: List<Article>,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(all = dimensionResource(id = R.dimen.article_list_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_list_spacing)),
    ) {
        items(
            items = articles,
            key = { articles ->
                articles.toString()
            },
        ) { article ->
            ArticleListItem(
                article = article,
                onBookmarkClick = onBookmarkClick,
                onArticleClick = onArticleClick,
                childModifier = childModifier,
            )
        }
    }
}

@Composable
private fun getColumnWidthPercentage(): Float {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp

    // https://developer.android.com/training/multiscreen/screensizes#TaskUseSWQuali
    // 600dp is the smallest width for a standard 7" tablet.
    val tabletWidthDp = 600
    val isTablet = screenWidth >= tabletWidthDp

    // If you don't want to use the configuration object, you can use the following code:
    // val isTablet = booleanResource(id = R.bool.isTablet)
    return if (isTablet) {
        0.5f // Set the column width to 50% of the screen width
    } else {
        1f
    }
}

@Preview(
    device = PIXEL_C,
)
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
