package com.sentics.compose_news.presentation.search

import androidx.paging.PagingData
import com.sentics.compose_news.presentation.category.ArticleView
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<ArticleView>>? = null
)
