package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.MyPage
import com.sentics.compose_news.domain.model.NewsConfig
import com.sentics.compose_news.domain.repository.NewsRepository
import javax.inject.Inject

class GetSpecificNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(config: NewsConfig): MyPage<Article> =
        newsRepository.getSpecificNews(config)
}
