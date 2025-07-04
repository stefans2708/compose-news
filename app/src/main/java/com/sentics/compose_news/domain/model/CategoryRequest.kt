package com.sentics.compose_news.domain.model

data class CategoryRequest(
    val pageToLoad: Int,
    val pageSize: Int = 20,
    val query: String? = null,
    val sources: String? = null,
    val sortBy: String? = null,
    val language: String? = null,
    val fromDate: String? = null,
    val toDate: String? = null
)
