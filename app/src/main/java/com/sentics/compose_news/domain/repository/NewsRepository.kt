package com.sentics.compose_news.domain.repository

import androidx.paging.PagingData
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.MyPage
import com.sentics.compose_news.domain.model.NewsConfig
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(query: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun getArticles(): Flow<List<Article>>

    suspend fun getArticle(url: String): Article?

    suspend fun getSpecificNews(config: NewsConfig): MyPage<Article>
}
