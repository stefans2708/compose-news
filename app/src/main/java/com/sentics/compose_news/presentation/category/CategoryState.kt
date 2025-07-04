package com.sentics.compose_news.presentation.category

data class CategoryState(
    val searchText: String = "",
    val displayedItems: List<ArticleView> = listOf(),
    val bottomSheetVisible: Boolean = false
)
