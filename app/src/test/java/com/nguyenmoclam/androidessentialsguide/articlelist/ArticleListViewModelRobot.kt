package com.nguyenmoclam.androidessentialsguide.articlelist

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.fake.FakeArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.testObserver

class ArticleListViewModelRobot {
    private lateinit var viewModel: ArticleListViewModel
    private val fakeArticleRepository = FakeArticleRepository()

    fun emitArticles(articles: List<Article>) =
        apply {
            fakeArticleRepository.emitArticles(articles)
        }

    fun emitFailure(error: Throwable) =
        apply {
            fakeArticleRepository.emitFailure(error)
        }

    fun buildViewModel() =
        apply {
            viewModel =
                ArticleListViewModel(
                    articleRepository = fakeArticleRepository,
                )
        }

    fun assertViewState(expectedViewState: ArticleListViewState) =
        apply {
            val actualViewState = viewModel.state.testObserver().observedValue
            assertThat(actualViewState).isEqualTo(expectedViewState)
        }

    fun assertNumberOfCallsToFetchArticles(expectedNumberOfCalls: Int) =
        apply {
            val actualCalls = fakeArticleRepository.getFetchArticleCallCount()
            assertThat(actualCalls).isEqualTo(expectedNumberOfCalls)
        }

    fun clickRetry() =
        apply {
            viewModel.retryClicked()
        }

    fun clickBookmark(article: Article) =
        apply {
            viewModel.bookmarkClicked(article)
        }

    fun assertArticleWasPersisted(article: Article) =
        apply {
            val wasPersisted = fakeArticleRepository.getPersistArticles().contains(article)
            assertThat(wasPersisted).isTrue()
        }
}
