package com.sentics.compose_news.presentation.category

import com.sentics.compose_news.domain.model.Article

data class ArticleView(
    val url: String,
    val title: String,
    val content: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val sourceTitle: String,
    val imageUrl: String
)

fun Article.toArticleView() = ArticleView(
    url = url,
    title = title,
    content = content,
    author = author.orEmpty(),
    description = description,
    publishedAt = publishedAt,
    sourceTitle = source.name,
    imageUrl = urlToImage
)
