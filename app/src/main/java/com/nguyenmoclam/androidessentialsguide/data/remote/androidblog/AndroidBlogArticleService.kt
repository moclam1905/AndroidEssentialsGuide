package com.nguyenmoclam.androidessentialsguide.data.remote.androidblog

import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.HtmlString

class AndroidBlogArticleService(
    private val androidBlogRetrofitAPI: AndroidBlogRetrofitAPI,
) : ArticleRepository {
    override suspend fun fetchArticles(): List<Article> {
        return androidBlogRetrofitAPI.getFeed().items?.map(
            AndroidBlogFeedItem::toArticle,
        ).orEmpty()
    }
}

private fun AndroidBlogFeedItem.toArticle(): Article {
    return Article(
        htmlTitle = HtmlString(this.title.orEmpty()),
        authorName = this.author?.name.orEmpty(),
        url = this.link?.href.orEmpty(),
    )
}
