package com.sentics.compose_news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sentics.compose_news.data.local.NewsDao
import com.sentics.compose_news.data.remote.NewsApi
import com.sentics.compose_news.data.remote.NewsPagingSource
import com.sentics.compose_news.data.remote.SearchNewsPagingSource
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.MyPage
import com.sentics.compose_news.domain.model.NewsConfig
import com.sentics.compose_news.domain.repository.NewsRepository
import com.sentics.compose_news.util.Constant
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow

    override fun searchNews(
        query: String,
        sources: List<String>
    ): Flow<PagingData<Article>> =
        Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(),
                    searchQuery = query
                )
            }
        ).flow

    override suspend fun upsertArticle(article: Article) =
        newsDao.upsert(article)

    override suspend fun deleteArticle(article: Article) =
        newsDao.delete(article)

    override fun getArticles(): Flow<List<Article>> =
        newsDao.getArticles()

    override suspend fun getArticle(url: String): Article? =
        newsDao.getArticleByUrl(url)

    override suspend fun getSpecificNews(config: NewsConfig): MyPage<Article> =
        newsApi.getSpecificNews(
            searchQuery = config.query,
            page = config.pageToLoad,
            pageSize = config.pageSize,
            sources = config.sources.orEmpty(),
            language = config.language,
            apiKey = Constant.API_KEY
        ).let { response ->
            MyPage(
                page = config.pageToLoad,
                totalCount = response.totalResult,
                items = response.articles,
            )
        }
}
