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
        private var article: Article? = null

        init {
            binding.root.setOnClickListener(this)
        }

        fun bindArticle(article: Article) {
            this.article = article
            binding.apply {
                articleTitle.text = article.htmlTitle.getSpannedString()
                articleAuthor.text =
                    itemView.context.getString(
                        R.string.by_author,
                        article.authorName,
                    )
            }
        }

        override fun onClick(v: View?) {
            article?.let { clickListener.onArticleClicked(it) }
        }
    }
}
