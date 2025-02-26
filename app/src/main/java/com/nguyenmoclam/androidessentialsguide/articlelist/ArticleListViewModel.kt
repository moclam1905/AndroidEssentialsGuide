package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import kotlinx.coroutines.launch

class ArticleListViewModel(articleRepository: ArticleRepository) : ViewModel() {
    private val mState: MutableLiveData<ArticleListViewState> = MutableLiveData()
    val state: LiveData<ArticleListViewState> = mState

    init {
        viewModelScope.launch {
            mState.value = ArticleListViewState.Loading
            // Fetch articles
            val fetchedArticles = articleRepository.fetchArticles()
            mState.value = ArticleListViewState.Success(fetchedArticles)
        }
    }
}
