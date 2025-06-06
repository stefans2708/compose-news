package com.sentics.compose_news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sentics.compose_news.domain.usecase.news.GetNews
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getNews: GetNews
) : ViewModel() {

    val news = getNews(Constant.NEWS_SOURCES)
        .cachedIn(viewModelScope)

}
