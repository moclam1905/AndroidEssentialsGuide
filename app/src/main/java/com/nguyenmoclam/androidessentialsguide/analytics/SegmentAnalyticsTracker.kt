package com.nguyenmoclam.androidessentialsguide.analytics

import com.segment.analytics.Analytics
import com.segment.analytics.Properties

/**
 * A concrete implementation of the [AnalyticsTracker] interface that uses Segment Analytics to track events.
 */
class SegmentAnalyticsTracker(
    private val segmentInstance: Analytics,
) : AnalyticsTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        val segmentProperties = Properties()

        event.properties.forEach { (key, value) ->
            segmentProperties[key] = value
        }
        segmentInstance.track(event.eventName, segmentProperties)
    }
}
