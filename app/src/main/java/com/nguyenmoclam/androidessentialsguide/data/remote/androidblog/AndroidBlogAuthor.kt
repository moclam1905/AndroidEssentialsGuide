package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "author", strict = false)
data class AndroidBlogAuthor(
    @field:Element(name = "name")
    @param:Element(name = "name")
    val name: String = "",
)
