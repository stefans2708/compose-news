package com.sentics.compose_news.domain.model

data class NewsConfig(
    val pageToLoad: Int,
    val pageSize: Int,
    val sources: String?,
    val query: String,
    val language: String
)