package com.nguyenmoclam.androidessentialsguide.articlelist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nguyenmoclam.androidessentialsguide.data.ArticleRepository
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogArticleService
import com.nguyenmoclam.androidessentialsguide.data.remote.androidblog.AndroidBlogRetrofitAPI
import com.nguyenmoclam.androidessentialsguide.databinding.FragmentArticleListBinding
import com.nguyenmoclam.androidessentialsguide.models.Article
import com.nguyenmoclam.androidessentialsguide.utils.visibleIf

class ArticleListFragment : Fragment(), ArticleClickListener {
    private var _binding: FragmentArticleListBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ArticleAdapter
    private lateinit var viewModel: ArticleListViewModel
    private val articlesListViewModelFactory =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository: ArticleRepository =
                    AndroidBlogArticleService(
                        androidBlogRetrofitAPI = AndroidBlogRetrofitAPI.getDefaultApi(),
                    )

                @Suppress("UNCHECKED_CAST")
                return ArticleListViewModel(
                    articleRepository = repository,
                ) as T
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, articlesListViewModelFactory)[ArticleListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        adapter =
            ArticleAdapter(
                clickListener = this,
            )
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.articleList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ArticleListFragment.adapter
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }
    }

    override fun onArticleClicked(article: Article) {
        val uri = Uri.parse(article.url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

    private fun subscribeToViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { viewState ->
            displayViewState(viewState)
        }
    }

    private fun displayViewState(viewState: ArticleListViewState) {
        binding.progressBar.visibleIf(viewState is ArticleListViewState.Loading)
        binding.articleList.visibleIf(viewState is ArticleListViewState.Success)

        if (viewState is ArticleListViewState.Success) {
            adapter.articles = viewState.articles
        }
    }
}
