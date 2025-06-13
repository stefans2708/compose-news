package com.sentics.compose_news.presentation.details

import com.sentics.compose_news.presentation.category.ArticleView

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: ArticleView) : DetailsEvent()

    object RemoveSideEffect : DetailsEvent()
}
