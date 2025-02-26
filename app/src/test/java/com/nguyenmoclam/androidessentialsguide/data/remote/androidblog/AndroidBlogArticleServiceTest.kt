package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AndroidBlogArticleServiceTest {
    private val testRobot = AndroidBlogArticleServiceTestRobot()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchValidAccountList() =
        runTest {
            val mockFeed =
                AndroidBlogFeed(
                    items =
                        listOf(
                            AndroidBlogFeedItem(
                                title = "Test Title",
                                author =
                                    AndroidBlogAuthor(
                                        name = "Test Author",
                                    ),
                                link =
                                    AndroidBlogLink(
                                        href = "https://test.com",
                                    ),
                            ),
                        ),
                )

            val expectedArticles =
                listOf(
                    Article(
                        htmlTitle = HtmlString("Test Title"),
                        authorName = "Test Author",
                        url = "https://test.com",
                    ),
                )

            testRobot.mockFeed(mockFeed).buildService().assertArticles(expectedArticles)
        }
}
