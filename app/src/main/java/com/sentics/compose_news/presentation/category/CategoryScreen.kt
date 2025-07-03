package com.sentics.compose_news.presentation.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.common.ArticleGridItem
import com.sentics.compose_news.presentation.common.LazyGridPager
import com.sentics.compose_news.presentation.common.SearchBar

@Composable
fun CategoryScreen(
    state: CategoryState,
    onSearchTextChange: (String) -> Unit,
    onSearch: () -> Unit,
    loadMore: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        SearchBar(
            modifier = Modifier.padding(
                start = Dimen.PaddingMedium1,
                end = Dimen.PaddingMedium1,
                bottom = Dimen.PaddingSmall
            ),
            readOnly = false,
            text = state.searchText,
            onValueChange = onSearchTextChange,
            onSearch = onSearch
        )

        LazyGridPager(
            columns = GridCells.Adaptive(minSize = 140.dp),
            loadMore = loadMore
        ) {
            items(items = state.displayedItems) {
                ArticleGridItem(article = it, onClick = {})
            }
        }
    }
}
