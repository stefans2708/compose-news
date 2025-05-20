package com.sentics.compose_news.domain.usecase.news

import androidx.paging.PagingData
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val repository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> =
        repository.getNews(sources)
}
