package com.nguyenmoclam.androidessentialsguide.articlelist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nguyenmoclam.androidessentialsguide.R
import com.nguyenmoclam.androidessentialsguide.databinding.ListItemArticleBinding
import com.nguyenmoclam.androidessentialsguide.models.Article

class ArticleAdapter(
    private val clickListener: ArticleClickListener,
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    var articles = listOf<Article>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemArticleBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding, clickListener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int,
    ) {
        val article = articles[position]
        holder.bindArticle(article)
    }

    class ArticleViewHolder(
        private val binding: ListItemArticleBinding,
        private val clickListener: ArticleClickListener,
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val viewModel = ArticleListItemViewModel()

        init {
            binding.root.setOnClickListener(this)
            binding.bookmarkButton.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            this.viewModel.article = article

            binding.articleTitle.text = viewModel.articleTitle
            binding.articleAuthor.text = viewModel.getAuthorText(itemView.resources)
            binding.bookmarkButton.setImageResource(viewModel.bookmarkButtonRes)
        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.bookmark_button -> {
                    viewModel.article?.let {
                        clickListener.onBookmarkClicked(it)
                    }
                }

                else -> {
                    viewModel.article?.let {
                        clickListener.onArticleClicked(it)
                    }
                }
            }
        }
    }
}
