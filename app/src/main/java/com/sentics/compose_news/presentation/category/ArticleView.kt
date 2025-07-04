package com.sentics.compose_news.presentation.category

import android.os.Parcelable
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.Source
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Parcelize
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
) : Parcelable {

    @IgnoredOnParcel
    val formattedDate =
        SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'", Locale.getDefault())
            .parse(publishedAt)
            ?.let { date -> SimpleDateFormat("dd.MM. 'at' HH:mm", Locale.getDefault()).format(date) }
            .orEmpty()
}

fun Article.toArticleView() = ArticleView(
    url = url,
    title = title,
    content = content,
    author = author.orEmpty(),
    description = description,
    publishedAt = publishedAt,
    sourceId = source.id,
    sourceTitle = source.name,
    imageUrl = urlToImage.orEmpty()
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
