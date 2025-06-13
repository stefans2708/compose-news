package com.sentics.compose_news.domain.usecase.news

import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.CategoryRequest
import com.sentics.compose_news.domain.model.MyPage
import com.sentics.compose_news.domain.repository.NewsRepository
import javax.inject.Inject

class GetSpecificNews @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(request: CategoryRequest): MyPage<Article> =
        newsRepository.getSpecificNews(request)
}
