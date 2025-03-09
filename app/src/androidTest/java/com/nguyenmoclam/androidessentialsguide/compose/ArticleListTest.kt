package com.nguyenmoclam.androidessentialsguide.compose

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
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
            (0..10).map {
                    index ->
                Article(
                    htmlTitle = HtmlString("Article Number: $index"),
                )
            }

        composeTestRule.setContent {
            ArticleList(articles = articles, onBookmarkClick = {}, onArticleClick = {})
        }

        composeTestRule.onRoot().printToLog("ArticleListItemTest")

        composeTestRule.onNodeWithText("Article Number: 0", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 1", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 2", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("Article Number: 3", useUnmergedTree = true).assertExists()
    }
}
