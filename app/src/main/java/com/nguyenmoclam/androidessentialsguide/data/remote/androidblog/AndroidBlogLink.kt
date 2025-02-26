package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "link", strict = false)
data class AndroidBlogLink(
    @field:Attribute(name = "href")
    @param:Attribute(name = "href")
    val href: String = "",
)
