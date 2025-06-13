package com.sentics.compose_news.domain.model

data class PageConfig(
    var pageToLoad: Int = 1,
    var isLoading: Boolean = false,
    var pageSize: Int = 20
)
