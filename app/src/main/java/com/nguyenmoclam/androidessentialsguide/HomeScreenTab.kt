package com.nguyenmoclam.androidessentialsguide

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.ui.graphics.vector.ImageVector

sealed class HomeScreenTab(
    val route: String,
    @StringRes val labelResourceId: Int,
    val icon: ImageVector,
) {
    data object AllArticles : HomeScreenTab(
        route = "all_articles",
        labelResourceId = R.string.all_articles,
        icon = Icons.AutoMirrored.Filled.LibraryBooks,
    )

    data object Bookmarks : HomeScreenTab(
        route = "bookmarks",
        labelResourceId = R.string.bookmarks,
        icon = Icons.Filled.Bookmarks,
    )
}
