package com.sentics.compose_news.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.common.ArticleList
import com.sentics.compose_news.presentation.common.SearchBar
import com.sentics.compose_news.presentation.navigation.Route

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = Dimen.PaddingMedium1,
                start = Dimen.PaddingMedium1,
                end = Dimen.PaddingMedium1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchNews) })

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

        state.articles
            ?.collectAsLazyPagingItems()
            ?.let { articles ->
                ArticleList(articles = articles) { navigate(Route.DetailsScreen.route) }
            }

    }
}