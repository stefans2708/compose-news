package com.sentics.compose_news.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sentics.compose_news.domain.usecase.news.DeleteArticle
import com.sentics.compose_news.domain.usecase.news.GetArticleByUrl
import com.sentics.compose_news.domain.usecase.news.UpsertArticle
import com.sentics.compose_news.presentation.category.toArticle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getArticleByUrl: GetArticleByUrl,
    private val deleteArticle: DeleteArticle,
    private val upsertArticle: UpsertArticle
) : ViewModel() {

    var messageSideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = getArticleByUrl(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article.toArticle())
                        messageSideEffect = "Article Saved"
                    } else {
                        deleteArticle(event.article.toArticle())
                        messageSideEffect = "Article Deleted"
                    }
                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                messageSideEffect = null
            }
        }
    }
}