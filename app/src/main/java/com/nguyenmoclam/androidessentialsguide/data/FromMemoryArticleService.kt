package com.nguyenmoclam.androidessentialsguide.data

import com.nguyenmoclam.androidessentialsguide.models.Article

class FromMemoryArticleService : ArticleRepository {
    override fun fetchArticles(): List<Article> {
        return listOf(
            Article(
                title = "Thermal Ca2+/Mg2+ exchange reactions to synthesize CO2 removal materials",
                authorName = "Yuxuan Chen & Matthew W. Kanan",
                url = "https://www.nature.com/articles/s41586-024-08499-2",
            ),
            Article(
                title = "Global biodiversity loss from outsourced deforestation",
                authorName = "R. Alex Wiebe & David S. Wilcove ",
                url = "https://www.nature.com/articles/s41586-024-08569-5",
            ),
            Article(
                title = "Clouds reduce downwelling longwave radiation over land in a warming climate",
                authorName = "Lei Liu, Yi Huang & John R. Gyakum ",
                url = "https://www.nature.com/articles/s41586-024-08323-x",
            ),
        )
    }
}
