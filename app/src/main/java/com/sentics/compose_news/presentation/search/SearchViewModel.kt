package com.sentics.compose_news.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.sentics.compose_news.domain.usecase.news.SearchNews
import com.sentics.compose_news.presentation.category.ArticleView
import com.sentics.compose_news.presentation.category.toArticleView
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
        val articles: Flow<PagingData<ArticleView>> =
            searchNews(
                query = _state.value.searchQuery,
                sources = Constant.NEWS_SOURCES_DEFAULT
            )
                .map {page -> page.map { it.toArticleView() }}
                .cachedIn(viewModelScope)

        _state.value = _state.value.copy(articles = articles)
    }
}
