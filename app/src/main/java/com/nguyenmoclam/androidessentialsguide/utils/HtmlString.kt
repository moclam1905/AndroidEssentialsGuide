package com.nguyenmoclam.androidessentialsguide.utils

import android.text.Spanned
import androidx.core.text.HtmlCompat

@JvmInline
value class HtmlString(private val input: String) {
    fun getSpannedString(): Spanned {
        return HtmlCompat.fromHtml(input, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}
