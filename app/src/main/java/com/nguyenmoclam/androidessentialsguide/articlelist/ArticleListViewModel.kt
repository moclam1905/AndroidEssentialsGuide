package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.DataResponse
import com.nguyenmoclam.androidessentialsguide.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel
    @Inject
    constructor(
        private val articleRepository: ArticleRepository,
    ) : ViewModel() {
        private val mState: MutableLiveData<ArticleListViewState> = MutableLiveData()
        val state: LiveData<ArticleListViewState> = mState

        init {
            fetchArticlesFromRepository()
        }

        private fun fetchArticlesFromRepository() {
            viewModelScope.launch {
                mState.value = ArticleListViewState.Loading

                val response = articleRepository.fetchArticles()
                mState.value =
                    when (response) {
                        is DataResponse.Success -> {
                            ArticleListViewState.Success(response.data)
                        }

                        is DataResponse.Error -> {
                            ArticleListViewState.Error(response.message)
                        }
                    }
            }
        }

        fun retryClicked() {
            fetchArticlesFromRepository()
        }

        fun bookmarkClicked(article: Article) {
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
    }
