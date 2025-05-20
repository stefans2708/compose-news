package com.sentics.compose_news.data.remote.dto

import com.sentics.compose_news.domain.model.Article

class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResult: Int
)
