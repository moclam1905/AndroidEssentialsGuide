package com.nguyenmoclam.androidessentialsguide.analytics

/**
 * @property[eventName] A unique identifier for the event being tracked.
 * @property[properties] A map of key-value pairs representing additional information about the event.
 * (such as the article title and whether it was bookmarked).
 * This information can be used to segment and filter events in the analytics dashboard.
 */
interface AnalyticsEvent {
    val eventName: String
    val properties: Map<String, Any>
}
