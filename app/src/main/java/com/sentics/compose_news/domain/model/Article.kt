package com.sentics.compose_news.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Article(
    @PrimaryKey val url: String,
    val author: String?,
    val content: String,
    val description: String,
    @ColumnInfo(name = "published_at") val publishedAt: String,
    val source: Source,
    val title: String,
    @ColumnInfo(name = "image_url") val urlToImage: String
) : Parcelable
