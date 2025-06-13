package com.sentics.compose_news.presentation.category

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sentics.compose_news.domain.model.CategoryRequest
import com.sentics.compose_news.domain.model.PageConfig
import com.sentics.compose_news.domain.usecase.news.GetSpecificNews
import com.sentics.compose_news.util.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val pager: Pager,
    private val getSpecificNews: GetSpecificNews
) : ViewModel() {

    private val TAG = CategoryViewModel::class.simpleName

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    init {
        pager.setup(::onLoadNextPage)
        loadPage()
    }

    fun loadPage() {
        viewModelScope.launch {
            pager.nextPage()
        }
    }

    private suspend fun onLoadNextPage(config: PageConfig) {
        try {
            val pageData = getSpecificNews.invoke(getCategoryRequest(config))
                .toMyPageView(itemMapper = { it.toArticleView() })

            _state.value = _state.value.copy(
                displayedItems = _state.value.displayedItems.plus(pageData.items)
            )
        } catch (e: Exception) {
            Log.e(TAG, "Failed to load page: ${config.pageToLoad}", e)
        }
    }

    private fun getCategoryRequest(config: PageConfig) = CategoryRequest(
        pageToLoad = config.pageToLoad,
        sources = Constant.NEWS_SOURCES.joinToString(",")
    )
}
