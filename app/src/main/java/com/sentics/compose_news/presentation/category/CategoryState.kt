package com.sentics.compose_news.presentation.category

data class CategoryState(
    val currentPageIndex: Int = 0,
    val displayedItems: List<ArticleView> = listOf()
)
