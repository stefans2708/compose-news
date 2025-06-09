package com.sentics.compose_news.presentation.bookmark

import com.sentics.compose_news.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = listOf()
)
