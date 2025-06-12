package com.sentics.compose_news.domain.model

data class MyPage<T>(
    val page: Int,
    val totalCount: Int,
    val items: List<T>
)