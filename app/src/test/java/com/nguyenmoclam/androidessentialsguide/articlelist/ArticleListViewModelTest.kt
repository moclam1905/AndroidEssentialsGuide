package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nguyenmoclam.androidessentialsguide.CoroutinesTestRule
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Rule
import org.junit.Test

class ArticleListViewModelTest {
    private val testRobot = ArticleListViewModelRobot()

    @JvmField
    @Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @JvmField
    @Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @After
    fun tearDown() {
        testRobot.cleanUp()
    }

    @Test
    fun successfulRequest(): Unit =
        runBlocking {
            val testArticles =
                listOf(
                    Article(htmlTitle = HtmlString("Test Title")),
                )

            testRobot.buildViewModel()
                .assertViewState(expectedViewState = ArticleListViewState.Loading)
                .emitArticles(testArticles)
                .assertViewState(expectedViewState = ArticleListViewState.Success(testArticles))
                .assertNumberOfCallsToFetchArticles(1)
        }

    @Test
    fun failedRequest(): Unit =
        runBlocking {
            val networkError = Throwable("Network Error")

            testRobot.buildViewModel().assertViewState(ArticleListViewState.Loading)
                .emitFailure(networkError).assertViewState(ArticleListViewState.Error(networkError))
                .assertNumberOfCallsToFetchArticles(1)
        }

    @Test
    fun retryFailedRequest(): Unit =
        runBlocking {
            val testArticles =
                listOf(
                    Article(htmlTitle = HtmlString("Test Title")),
                )

            val networkError = Throwable("Network Error")

            testRobot.buildViewModel().assertViewState(ArticleListViewState.Loading)
                .emitFailure(networkError).clickRetry().assertViewState(ArticleListViewState.Loading)
                .emitArticles(testArticles).assertViewState(ArticleListViewState.Success(testArticles))
                .assertNumberOfCallsToFetchArticles(2)
        }

    @Test
    fun clickingBookmarkShouldPersistArticle(): Unit =
        runBlocking {
            val unBookmarkedArticle = Article(htmlTitle = HtmlString("Test Title"))
            val bookmarkedArticle = unBookmarkedArticle.copy(bookmark = true)

            val initialArticles = listOf(unBookmarkedArticle)
            val updatedArticles = listOf(bookmarkedArticle)

            testRobot.buildViewModel().emitArticles(initialArticles)
                .assertViewState(ArticleListViewState.Success(initialArticles))
                .clickBookmark(unBookmarkedArticle)
                .assertViewState(ArticleListViewState.Success(updatedArticles))
                .assertArticleWasPersisted(bookmarkedArticle)
        }
}
