package com.nguyenmoclam.androidessentialsguide.compose

import com.nguyenmoclam.androidessentialsguide.ArticleListScreenContent
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.nguyenmoclam.androidessentialsguide.articlelist.ArticleListViewState
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import org.junit.Rule
import org.junit.Test

class ArticleListScreenContentTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun createSuccessState() {
        val articleTitle = "Test Article"
        val testArticle =
            Article(
                htmlTitle = HtmlString(articleTitle),
            )
        val articleList = listOf(testArticle)

        val viewState = ArticleListViewState.Success(articleList)

        composeTestRule.setContent {
            ArticleListScreenContent(
                currentState = viewState,
                onBookmarkClick = {},
                onArticleClick = {},
            )
        }

        composeTestRule.onNodeWithText(articleTitle).assertIsDisplayed()
    }

    @Test
    fun createLoadingState() {
        val viewState = ArticleListViewState.Loading
        composeTestRule.setContent {
            ArticleListScreenContent(
                currentState = viewState,
                onBookmarkClick = {},
                onArticleClick = {},
            )
        }
        composeTestRule.onNode(hasProgressBarRangeInfo(ProgressBarRangeInfo.Indeterminate))
            .assertIsDisplayed()
    }
}
