package com.nguyenmoclam.androidessentialsguide.analytics

/**
 * We want to create our own contract for tracking analytics
 * events in the codebase, so that we aren't tightly coupled
 * to any specific third party vendor.
 *
 * By having our ViewModels depend on this interface, rather than
 * directly on Firebase, Segment, etc - we gives ourselves the capability
 * to swap between those tools, and also easily create fake implementations
 * in unit tests.
 */
interface AnalyticsTracker {
    /**
     * This method should be used to track any analytics event
     */
    fun trackEvent(event: AnalyticsEvent)
}
