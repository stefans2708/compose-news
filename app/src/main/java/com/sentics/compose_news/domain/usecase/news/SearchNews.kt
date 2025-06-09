package com.sentics.compose_news.domain.usecase.news

import androidx.paging.PagingData
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(query: String, sources: List<String>): Flow<PagingData<Article>> =
        newsRepository.searchNews(query, sources)
}