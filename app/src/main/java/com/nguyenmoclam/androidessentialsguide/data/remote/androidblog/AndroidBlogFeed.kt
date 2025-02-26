package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "feed", strict = false)
data class AndroidBlogFeed(
    @field:ElementList(name = "entry", inline = true)
    @param:ElementList(name = "entry", inline = true)
    val items: List<AndroidBlogFeedItem>? = null,
)
