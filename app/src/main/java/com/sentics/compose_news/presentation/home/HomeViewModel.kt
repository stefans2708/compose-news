package com.sentics.compose_news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sentics.compose_news.domain.usecase.news.GetNews
import com.sentics.compose_news.presentation.category.ArticleView
import com.sentics.compose_news.presentation.category.toArticleView
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getNews: GetNews
) : ViewModel() {

    val news: Flow<PagingData<ArticleView>> =
        getNews(Constant.NEWS_SOURCES_DEFAULT)
            .map { page -> page.map { it.toArticleView() } }
            .cachedIn(viewModelScope)

}
