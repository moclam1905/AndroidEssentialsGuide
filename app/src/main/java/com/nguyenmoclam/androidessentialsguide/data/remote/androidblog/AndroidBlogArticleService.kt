package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article

class AndroidBlogArticleService(
    private val androidBlogRetrofitAPI: AndroidBlogRetrofitAPI,
) : ArticleRepository {
    override suspend fun fetchArticles(): List<Article> {
        return androidBlogRetrofitAPI.getFeed().items.map(
            AndroidBlogFeedItem::toArticle,
        )
    }
}

private fun AndroidBlogFeedItem.toArticle(): Article {
    return Article(
        title = this.title,
        authorName = this.author.name,
        url = this.link.href,
    )
}
