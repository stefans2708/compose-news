package com.sentics.compose_news.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.sentics.compose_news.domain.usecase.news.SearchNews
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchNews: SearchNews
) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.query)
            }

            is SearchEvent.SearchNews -> executeSearchNews()
        }
    }

    private fun executeSearchNews() {
        val articles = searchNews(
            query = _state.value.searchQuery,
            sources = Constant.NEWS_SOURCES
        ).cachedIn(viewModelScope)

        _state.value = _state.value.copy(articles = articles)
    }
}
