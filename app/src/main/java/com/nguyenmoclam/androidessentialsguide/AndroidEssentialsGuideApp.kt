package com.nguyenmoclam.androidessentialsguide

import android.app.Application
import com.segment.analytics.Analytics
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AndroidEssentialsGuideApp : Application() {
    override fun onCreate() {
        super.onCreate()

        initializeSegment()
    }

    private fun initializeSegment() {
        val analytics: Analytics =
            Analytics.Builder(applicationContext, BuildConfig.SEGMENT_API_KEY)
                .trackApplicationLifecycleEvents()
                .recordScreenViews()
                .build()

        Analytics.setSingletonInstance(analytics)
    }
}
