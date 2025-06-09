package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.data.local.NewsDao
import com.sentics.compose_news.domain.model.Article
import javax.inject.Inject

class GetArticleByUrl @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(url: String): Article? =
        newsDao.getArticleByUrl(url)
}
