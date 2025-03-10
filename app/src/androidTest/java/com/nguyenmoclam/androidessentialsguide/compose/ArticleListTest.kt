package com.nguyenmoclam.androidessentialsguide.compose

import android.content.res.Configuration
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasScrollToIndexAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.printToLog
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displayArticleList() {
        val articles =
            (0..10).map { index ->
                Article(
                    htmlTitle = HtmlString("Article Number: $index"),
                )
            }

        composeTestRule.setContent {
            ArticleList(articles = articles, onBookmarkClick = {}, onArticleClick = {})
        }

        composeTestRule.onRoot().printToLog("ArticleListItemTest")
        val indexes = (0..10)
        indexes.forEach { index ->
            composeTestRule.onNode(hasScrollToIndexAction()).performScrollToIndex(index)
            composeTestRule.onNodeWithText("Article Number: $index", useUnmergedTree = true)
                .assertExists()
        }
    }

    @Test
    fun clickingBookmarkIconUpdatesUI() {
        val testArticle = Article(htmlTitle = HtmlString("Test Title"), bookmark = false)
        composeTestRule.setContent {
            var articleList: List<Article> by remember { mutableStateOf(listOf(testArticle)) }

            ArticleList(
                articles = articleList,
                onBookmarkClick = {
                    articleList =
                        articleList.map {
                            it.copy(bookmark = true)
                        }
                },
                onArticleClick = {},
            )
        }

        composeTestRule.onNodeWithContentDescription("Not bookmarked").assertExists()
        composeTestRule.onNodeWithContentDescription("Not bookmarked").performClick()

        composeTestRule.onNodeWithContentDescription("Bookmarked").assertExists()
    }

    @Test
    fun renderSmallColumnOnTablets() {
        val articles =
            (0..10).map { index ->
                Article(htmlTitle = HtmlString("Article Number: $index"))
            }

        composeTestRule.setContent {
            val tabletConfiguration =
                Configuration().apply {
                    screenWidthDp = 600
                }

            CompositionLocalProvider(LocalConfiguration provides tabletConfiguration) {
                ArticleList(articles = articles, onBookmarkClick = {}, onArticleClick = {})
            }
        }

        composeTestRule.onNode(SemanticsMatcher.expectValue(ColumnWidthKey, 0.5f)).assertExists()
    }

    @Test
    fun renderFullColumnOnPhones() {
        val articles =
            (0..10).map { index ->
                Article(htmlTitle = HtmlString("Article Number: $index"))
            }

        composeTestRule.setContent {
            val phoneConfiguration =
                Configuration().apply {
                    screenWidthDp = 320
                }

            CompositionLocalProvider(LocalConfiguration provides phoneConfiguration) {
                ArticleList(articles = articles, onBookmarkClick = {}, onArticleClick = {})
            }
        }

        composeTestRule.onNode(SemanticsMatcher.expectValue(ColumnWidthKey, 1f)).assertExists()
    }
}
