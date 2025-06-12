package com.sentics.compose_news.presentation.category

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sentics.compose_news.domain.model.NewsConfig
import com.sentics.compose_news.domain.usecase.news.GetSpecificNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getSpecificNews: GetSpecificNews
) : ViewModel() {

    private val _state = mutableStateOf(CategoryState())
    val state: State<CategoryState> = _state

    init {
        loadPage()
    }

    fun loadPage() {
        viewModelScope.launch {
            val pagingConfig = getPagingConfig()

            try {
                val pageData = getSpecificNews.invoke(pagingConfig)
                    .toMyPageView(itemMapper = { it.toArticleView() })

                _state.value = _state.value.copy(
                    displayedItems = _state.value.displayedItems.plus(pageData.items),
                    currentPageIndex = pagingConfig.pageToLoad
                )

            } catch (e: Exception) {
                Log.e(CategoryViewModel::class.java.simpleName, "Failed to load page: ${pagingConfig.pageToLoad}", e)
            }

        }
    }

    private fun getPagingConfig() =
        NewsConfig(
            pageToLoad = _state.value.currentPageIndex + 1,
            pageSize = 20,
            sources = "",
            language = "",
            query = ""
        )
}
