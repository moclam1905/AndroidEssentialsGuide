package com.nguyenmoclam.androidessentialsguide.models

import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

data class Article(
    val htmlTitle: HtmlString = HtmlString(""),
    val authorName: String = "",
    val url: String = "",
    val bookmark: Boolean = false,
    val tags: List<String> = emptyList(),
    val publishedDate: String = "",
)
