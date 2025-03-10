package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsTracker
import com.nguyenmoclam.androidessentialsguide.analytics.BookmarkedArticleAnalyticsEvent
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.launch

abstract class BaseArticleListViewModel(
    private val articleRepository: ArticleRepository,
    private val analyticsTracker: AnalyticsTracker,
) : ViewModel() {
    private val mState: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = mState

    @get:StringRes
    abstract val emptyStateMessageTextRes: Int

    init {
        fetchArticlesFromRepository()
    }

    private fun fetchArticlesFromRepository() {
        viewModelScope.launch {
            mState.value = ArticleListViewState.Loading

            articleRepository.fetchArticles().collect { response ->
                mState.value =
                    when (response) {
                        is DataResponse.Success -> {
                            handleSuccessNetworkResponse(response)
                        }

                        is DataResponse.Error -> {
                            ArticleListViewState.Error(response.message)
                        }
                    }
            }
        }
    }

    fun retryClicked() {
        fetchArticlesFromRepository()
    }

    fun bookmarkClicked(article: Article) {
        val updatedArticle = article.copy(bookmark = !article.bookmark)

        viewModelScope.launch {
            articleRepository.persistArticle(updatedArticle)
        }

        val event =
            BookmarkedArticleAnalyticsEvent(
                articleTitle = updatedArticle.htmlTitle.getInput(),
                isBookmarked = updatedArticle.bookmark,
            )

        analyticsTracker.trackEvent(event)

        val currentList = (mState.value as? ArticleListViewState.Success)?.articles.orEmpty()
        val updatedList =
            currentList.map { item ->
                val isSameItem = item.htmlTitle == article.htmlTitle
                if (isSameItem) {
                    item.copy(bookmark = !item.bookmark)
                } else {
                    item
                }
            }
        mState.value = ArticleListViewState.Success(updatedList)
    }

    private fun handleSuccessNetworkResponse(response: DataResponse.Success<List<Article>>): ArticleListViewState {
        return if (response.data.isEmpty()) {
            ArticleListViewState.Empty
        } else {
            ArticleListViewState.Success(response.data)
        }
    }
}
