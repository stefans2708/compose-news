package com.sentics.compose_news.presentation.category

import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.Source

data class ArticleView(
    val url: String,
    val title: String,
    val content: String,
    val author: String,
    val description: String,
    val publishedAt: String,
    val sourceTitle: String,
    val sourceId: String,
    val imageUrl: String
)

fun Article.toArticleView() = ArticleView(
    url = url,
    title = title,
    content = content,
    author = author.orEmpty(),
    description = description,
    publishedAt = publishedAt,
    sourceId = source.id,
    sourceTitle = source.name,
    imageUrl = urlToImage
)

fun ArticleView.toArticle() = Article(
    url = url,
    author = author,
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = Source(sourceId, sourceTitle),
    title = title,
    urlToImage = imageUrl
)
