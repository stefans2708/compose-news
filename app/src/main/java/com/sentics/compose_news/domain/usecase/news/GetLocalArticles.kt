package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.data.local.NewsDao
import javax.inject.Inject

class GetLocalArticles @Inject constructor(
    private val newsDao: NewsDao
) {

    operator fun invoke() = newsDao.getArticles()
}
