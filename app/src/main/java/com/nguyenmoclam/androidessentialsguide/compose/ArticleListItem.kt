package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

@Composable
fun ArticleListItem(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    article: Article,
    onBookmarkClick: (Article) -> Unit,
    onArticleClick: (Article) -> Unit,
) {
    Card(onClick = {
        onArticleClick(article)
    }) {
        Row(
            modifier =
                modifier
                    .padding(
                        all = dimensionResource(id = R.dimen.article_list_item_padding),
                    )
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ArticleTitleAndAuthor(
                article = article,
                modifier = Modifier.weight(1f),
            )
            BookmarkButton(
                modifier = childModifier,
                article = article,
                onClick = onBookmarkClick,
            )
        }
    }
}

@Composable
private fun BookmarkButton(
    modifier: Modifier,
    article: Article,
    onClick: (Article) -> Unit,
) {
    val iconRes =
        if (article.bookmark) {
            R.drawable.ic_bookmark_selected
        } else {
            R.drawable.ic_bookmark_unselected
        }

    val contentDescription =
        if (article.bookmark) {
            "Bookmarked"
        } else {
            "Not bookmarked"
        }
    IconButton(
        onClick = { onClick(article) },
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary),
        )
    }
}

@Composable
private fun ArticleTagsRow(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    article: Article,
) {
    Row(
        modifier =
            modifier.semantics {
                contentDescription = "Article tags"
            },
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.article_tags_spacing)),
    ) {
        article.tags.forEach { tag ->
            Text(
                text = tag,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier =
                    childModifier
                        .background(
                            color =
                                MaterialTheme.colorScheme.primary.copy(
                                    alpha = 0.5f,
                                ),
                            shape = CircleShape,
                        )
                        .padding(
                            vertical = dimensionResource(id = R.dimen.article_tag_vertical_padding),
                            horizontal = dimensionResource(id = R.dimen.article_tag_horizontal_padding),
                        ),
            )
        }
    }
}

@Composable
private fun ArticleTitleAndAuthor(
    modifier: Modifier = Modifier,
    childModifier: Modifier = Modifier,
    article: Article,
) {
    val authorLabelWidthRatio = 0.75f
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = article.htmlTitle.getInput(),
            style = MaterialTheme.typography.titleMedium,
            modifier =
                childModifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = R.dimen.article_title_bottom_padding)),
        )
        Text(
            text = "By ${article.authorName}",
            style = MaterialTheme.typography.labelMedium,
            modifier =
                childModifier
                    .fillMaxWidth(authorLabelWidthRatio)
                    .padding(bottom = dimensionResource(id = R.dimen.article_author_bottom_padding)),
        )
        if (article.tags.isNotEmpty()) {
            ArticleTagsRow(article = article, childModifier = childModifier)
        }
    }
}

@Preview(
    device = Devices.PIXEL_C,
)
@Composable
fun PreviewArticleListItem() {
    val article =
        Article(
            htmlTitle = HtmlString("Jetpack Compose"),
            authorName = "Moclam",
            tags = listOf("Jetpack", "Compose"),
            bookmark = true,
        )

    StudyGuideTheme {
        Surface {
            ArticleListItem(
                article = article,
                onBookmarkClick = {},
                onArticleClick = {},
            )
        }
    }
}
