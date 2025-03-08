package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.data.local.PersistableArticle
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AndroidBlogArticleServiceTest {
    private val testRobot = AndroidBlogArticleServiceTestRobot()

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

    @Test
    fun fetchArticlesWithOneBookmarked(): Unit =
        runBlocking {
            val testUrl = "Test Url"
            val mocked =
                AndroidBlogFeed(
                    items =
                        listOf(
                            AndroidBlogFeedItem(
                                title = "Test Title",
                                author = AndroidBlogAuthor(name = "Test Author"),
                                link = AndroidBlogLink(href = testUrl),
                            ),
                        ),
                )
            val mockBookmarks = listOf(PersistableArticle(url = testUrl, bookmarked = true))

            val expectedArticles =
                listOf(
                    Article(
                        htmlTitle = HtmlString("Test Title"),
                        authorName = "Test Author",
                        url = testUrl,
                        bookmark = true,
                    ),
                )

            testRobot.mockFeed(mocked).mockBookmarks(mockBookmarks).buildService()
                .assertArticles(expectedArticles)
        }

    @Test
    fun persistArticlesShouldBeCallDatabaseInsert(): Unit =
        runBlocking {
            testRobot.buildService().persistArticle(Article()).assertInsertArticleToDBCallCount(1)
        }
}
