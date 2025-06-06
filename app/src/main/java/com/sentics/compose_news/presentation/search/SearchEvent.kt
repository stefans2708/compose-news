package com.sentics.compose_news.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val query: String) : SearchEvent()
    object SearchNews : SearchEvent()
}