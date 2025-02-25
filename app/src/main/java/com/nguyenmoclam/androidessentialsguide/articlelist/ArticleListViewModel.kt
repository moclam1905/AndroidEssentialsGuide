package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article
import kotlinx.coroutines.launch

class ArticleListViewModel(articleRepository: ArticleRepository) : ViewModel() {
    private val mArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val article: LiveData<List<Article>> = mArticles

    init {
        viewModelScope.launch {
            val fetchedArticles = articleRepository.fetchArticles()
            mArticles.value = fetchedArticles
        }
    }
}
