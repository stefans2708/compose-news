package com.sentics.compose_news.domain.repository

import androidx.paging.PagingData
import com.sentics.compose_news.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}
