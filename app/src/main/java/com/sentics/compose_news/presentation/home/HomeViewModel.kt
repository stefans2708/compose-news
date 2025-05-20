package com.sentics.compose_news.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sentics.compose_news.domain.usecase.news.GetNews
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNews: GetNews
) : ViewModel() {

    val news = getNews(sources = listOf("bbc-news", "abc-news", "al-jazzera-english"))
        .cachedIn(viewModelScope)

}
