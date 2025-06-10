package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.domain.repository.NewsRepository
import javax.inject.Inject

class GetLocalArticles @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke() = newsRepository.getArticles()
}
