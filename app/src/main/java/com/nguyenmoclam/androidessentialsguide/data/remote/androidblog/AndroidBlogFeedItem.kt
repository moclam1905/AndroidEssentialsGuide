package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class AndroidBlogFeedItem(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String = "",
    @field:Element(name = "author")
    @param:Element(name = "author")
    val author: AndroidBlogAuthor = AndroidBlogAuthor(),
    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: AndroidBlogLink = AndroidBlogLink(),
)
