package com.sentics.compose_news.presentation.bookmark

import com.sentics.compose_news.presentation.category.ArticleView

data class BookmarkState(
    val articles: List<ArticleView> = listOf()
)
