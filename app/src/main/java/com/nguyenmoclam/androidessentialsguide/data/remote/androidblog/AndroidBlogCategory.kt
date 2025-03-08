package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "category", strict = false)
data class AndroidBlogCategory(
    @field:Attribute(name = "term")
    @param:Attribute(name = "term")
    val term: String? = null,
)
