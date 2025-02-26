package com.nguyenmoclam.androidessentialsguide.utils

import android.view.View

fun View.visibleIf(condition: Boolean)  {
    this.visibility =
        if (condition)
            {
                View.VISIBLE
            } else {
            View.GONE
        }
}
