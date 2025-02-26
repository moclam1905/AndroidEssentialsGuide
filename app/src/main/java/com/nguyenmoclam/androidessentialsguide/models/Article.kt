package com.nguyenmoclam.androidessentialsguide.models

import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

data class Article(
    val htmlTitle: HtmlString,
    val authorName: String,
    val url: String,
)
