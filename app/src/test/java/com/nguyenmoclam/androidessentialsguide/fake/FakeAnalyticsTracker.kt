package com.nguyenmoclam.androidessentialsguide.fake

import com.google.common.truth.Truth.assertThat
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsEvent
import com.nguyenmoclam.androidessentialsguide.analytics.AnalyticsTracker

class FakeAnalyticsTracker : AnalyticsTracker {
    private val trackedEvents = mutableListOf<AnalyticsEvent>()

    override fun trackEvent(event: AnalyticsEvent) {
        trackedEvents.add(event)
    }

    fun assertEventTracked(expectedEvent: AnalyticsEvent) {
        assertThat(trackedEvents).contains(expectedEvent)
    }
}
