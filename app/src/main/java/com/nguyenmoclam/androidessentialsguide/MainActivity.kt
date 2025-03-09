package com.nguyenmoclam.androidessentialsguide

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.nguyenmoclam.androidessentialsguide.articlelist.AndroidBlogArticleListViewModel
import com.nguyenmoclam.androidessentialsguide.bookmarks.BookmarkListViewModel
import com.nguyenmoclam.androidessentialsguide.compose.StudyGuideTheme
import com.nguyenmoclam.androidessentialsguide.models.Article
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyGuideTheme {
                val navController = rememberNavController()
                val homeScreenTabs = listOf(HomeScreenTab.AllArticles, HomeScreenTab.Bookmarks)

                Scaffold(
                    topBar = {
                        StudyGuideAppBar()
                    },
                    bottomBar = {
                        NavigationBar {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentRoute = navBackStackEntry?.destination?.route
                            homeScreenTabs.forEach { tab ->
                                BottomNavigationItemForTab(tab, currentRoute, navController)
                            }
                        }
                    },
                ) { paddingValues ->
                    NavHost(navController, startDestination = HomeScreenTab.AllArticles.route) {
                        composable(HomeScreenTab.AllArticles.route) {
                            val viewModel: AndroidBlogArticleListViewModel by viewModels()
                            ArticleListScreen(
                                viewModel = viewModel,
                                onBookmarkClick = viewModel::bookmarkClicked,
                                onArticleClick = ::showArticlesBrowser,
                                paddingValue = paddingValues,
                            )
                        }
                        composable(HomeScreenTab.Bookmarks.route) {
                            val viewModel: BookmarkListViewModel by viewModels()
                            ArticleListScreen(
                                viewModel = viewModel,
                                onBookmarkClick = viewModel::bookmarkClicked,
                                onArticleClick = ::showArticlesBrowser,
                                paddingValue = paddingValues,
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun showArticlesBrowser(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    @Composable
    private fun StudyGuideAppBar() {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
        )
    }

    @Composable
    fun RowScope.BottomNavigationItemForTab(
        tab: HomeScreenTab,
        currentRoute: String?,
        navController: NavController,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    tab.icon,
                    contentDescription = stringResource(id = tab.labelResourceId),
                )
            },
            label = {
                Text(
                    stringResource(tab.labelResourceId),
                )
            },
            selected = (currentRoute == tab.route),
            onClick = {
                navController.navigate(tab.route) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
        )
    }
}
