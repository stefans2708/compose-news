package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.repository.NewsRepository
import javax.inject.Inject

class UpsertArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article) =
        newsRepository.upsertArticle(article)
}
