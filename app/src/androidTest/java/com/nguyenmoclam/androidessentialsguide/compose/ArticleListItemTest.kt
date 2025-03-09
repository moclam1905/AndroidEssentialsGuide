package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayBookmarkedArticleWithNoTags()  {
        val title = "My Title"
        val article =
            Article(
                htmlTitle = HtmlString(title),
                authorName = "Author Name",
                bookmark = true,
                tags = emptyList(),
            )

        composeTestRule.setContent {
            ArticleListItem(article = article, onBookmarkClick = {}, onArticleClick = {})
        }

        composeTestRule.onRoot().printToLog("ArticleListItemTest")

        composeTestRule.onNodeWithText(title, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("By Author Name", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Bookmarked").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Article tags").assertDoesNotExist()
    }

    @Test
    fun displayNonBookmarkedArticleWithTags() {
        val title = "My Title"

        val article =
            Article(
                htmlTitle = HtmlString(title),
                authorName = "ML",
                bookmark = false,
                tags = listOf("Jetpack", "Compose"),
            )

        composeTestRule.setContent {
            ArticleListItem(
                article = article,
                onBookmarkClick = { },
                onArticleClick = { },
            )
        }

        composeTestRule.onNodeWithText(title, useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("By ML", useUnmergedTree = true)
            .assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Not bookmarked").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("Article tags").assertIsDisplayed()

        composeTestRule.onNodeWithText("Jetpack", useUnmergedTree = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("Compose", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun handleClickListeners()  {
        val title = "My Title"

        val article =
            Article(
                htmlTitle = HtmlString(title),
                authorName = "ML",
                bookmark = true,
                tags = emptyList(),
            )

        var bookmarkClicked = false
        var articleClicked = false

        composeTestRule.setContent {
            ArticleListItem(
                article = article,
                onBookmarkClick = {
                    bookmarkClicked = true
                },
                onArticleClick = {
                    articleClicked = true
                },
            )
        }

        composeTestRule.onNodeWithContentDescription("Bookmarked").performClick()
        assertThat(bookmarkClicked).isTrue()

        composeTestRule.onRoot().performClick()
        assertThat(articleClicked).isTrue()
    }
}
