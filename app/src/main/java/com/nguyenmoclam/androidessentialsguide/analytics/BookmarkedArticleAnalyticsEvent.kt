package com.nguyenmoclam.androidessentialsguide.analytics

/**
 * This analytics event will be fired every time a user bookmarks or unbookmarks an article.
 * @property[articleTitle] The title of the article that was bookmarked.
 * @property[isBookmarked] True if the user is bookmarked, false otherwise.
 */
data class BookmarkedArticleAnalyticsEvent(
    private val articleTitle: String,
    private val isBookmarked: Boolean,
) : AnalyticsEvent {
    override val eventName: String
        get() = "bookmarked_article"

    override val properties: Map<String, Any>
        get() =
            mapOf(
                "article_title" to articleTitle,
                "is_bookmarked" to isBookmarked,
            )
}
