package com.nguyenmoclam.androidessentialsguide.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article

class ArticleViewModel(articleRepository: ArticleRepository) : ViewModel() {
    private val mArticles: MutableLiveData<List<Article>> = MutableLiveData()
    val article: LiveData<List<Article>> = mArticles

    init {
        val fetchedArticles = articleRepository.fetchArticles()
        mArticles.value = fetchedArticles
    }
}
