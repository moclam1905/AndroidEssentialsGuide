package com.nguyenmoclam.androidessentialsguide.analytics

import androidx.core.os.bundleOf
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

/**
 * This is a concrete implementation of the [AnalyticsTracker] interface that uses Firebase Analytics to track events.
 */
class FirebaseAnalyticsTracker : AnalyticsTracker {
    override fun trackEvent(event: AnalyticsEvent) {
        Firebase.analytics.logEvent(
            event.eventName,
            bundleOf(*event.properties.toList().toTypedArray()),
        )
    }
}
