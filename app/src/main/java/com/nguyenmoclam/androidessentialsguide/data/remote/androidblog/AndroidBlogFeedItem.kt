package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class AndroidBlogFeedItem(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String? = null,
    @field:Element(name = "author")
    @param:Element(name = "author")
    val author: AndroidBlogAuthor? = null,
    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: AndroidBlogLink? = null,
    @field:ElementList(name = "category", inline = true)
    @param:ElementList(name = "category", inline = true)
    val categories: List<AndroidBlogCategory>? = null,
    @field:Element(name = "published")
    @param:Element(name = "published")
    val published: String? = null,
)
