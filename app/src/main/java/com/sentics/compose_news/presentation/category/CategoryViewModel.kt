package com.sentics.compose_news.presentation.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sentics.compose_news.domain.model.CategoryRequest
import com.sentics.compose_news.domain.model.PageConfig
import com.sentics.compose_news.domain.usecase.news.GetSpecificNews
import com.sentics.compose_news.presentation.util.TAG
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val pager: Pager,
    private val getSpecificNews: GetSpecificNews
) : ViewModel() {

    private val _showBottomSheet = MutableStateFlow(false)
    private val _searchText = MutableStateFlow("")
    private val _articles = MutableStateFlow<List<ArticleView>>(listOf())

    val state: StateFlow<CategoryState> =
        combine(_searchText, _articles, _showBottomSheet) { query, articles, showBottomSheet ->
            CategoryState(
                searchText = query,
                displayedItems = articles,
                bottomSheetVisible = showBottomSheet
            )
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CategoryState()
        )

    init {
        pager.setup(::onLoadNextPage)
        loadPage()

        _searchText
            .debounce(300)
            .distinctUntilChanged()
            .onEach { searchArticles() }
            .launchIn(viewModelScope)

//        Same is achieved with this:
//        viewModelScope.launch {
//            _searchText
//                .debounce(300)
//                .distinctUntilChanged()
//                .collect { searchArticles() }
//        }
    }

    fun loadPage() {
        viewModelScope.launch {
            pager.nextPage()
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun onSearchQueryChange(text: String) {
        Log.d(TAG, "onSearchQueryChange: $text")
        _searchText.value = text
    }

    fun searchArticles() {
        Log.d(TAG, "searchArticles: $state")
        _articles.value = listOf()
        pager.reset()
        loadPage()
    }

    fun bottomSheetTrigger() {
        _showBottomSheet.value = !_showBottomSheet.value
    }

    private suspend fun onLoadNextPage(config: PageConfig) {
        try {
            getSpecificNews.invoke(getCategoryRequest(config))
                .toMyPageView(itemMapper = { it.toArticleView() })
                .let { pageData ->
                    _articles.value = _articles.value.plus(pageData.items)
                }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load page: ${config.pageToLoad}", e)
        }
    }

    private fun getCategoryRequest(config: PageConfig) = CategoryRequest(
        pageToLoad = config.pageToLoad,
        sources = Constant.NEWS_SOURCES.joinToString(","),
        query = _searchText.value
    )
}
