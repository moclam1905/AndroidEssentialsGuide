package com.nguyenmoclam.androidessentialsguide.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.nguyenmoclam.androidessentialsguide.compose.ArticleCollection
import com.nguyenmoclam.androidessentialsguide.compose.StudyGuideTheme
import com.nguyenmoclam.androidessentialsguide.models.Article

abstract class BaseArticleListFragment : Fragment(), ArticleClickListener {
    abstract val viewModel: BaseArticleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            StudyGuideTheme {
                val state = viewModel.state.observeAsState()
                when (val currentState = state.value) {
                    is ArticleListViewState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier.wrapContentSize(align = Alignment.Center),
                        )
                    }

                    is ArticleListViewState.Success -> {
                        ArticleCollection(
                            articles = currentState.articles,
                            onBookmarkClick = { article -> this.onBookmarkClicked(article) },
                            onArticleClick = { article -> this.onArticleClicked(article) },
                        )
                    }

                    ArticleListViewState.Empty -> {}
                    is ArticleListViewState.Error -> {
                    }

                    null -> {}
                }
            }
        }

        return composeView
    }

    override fun onArticleClicked(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    override fun onBookmarkClicked(article: Article) {
        viewModel.bookmarkClicked(article)
    }
}
