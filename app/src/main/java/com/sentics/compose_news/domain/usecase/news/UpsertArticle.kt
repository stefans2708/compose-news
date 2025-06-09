package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.data.local.NewsDao
import com.sentics.compose_news.domain.model.Article
import javax.inject.Inject

class UpsertArticle @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article) =
        newsDao.upsert(article)
}
