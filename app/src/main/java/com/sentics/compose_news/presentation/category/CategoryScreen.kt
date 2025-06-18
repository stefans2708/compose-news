package com.sentics.compose_news.presentation.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sentics.compose_news.presentation.common.ArticleGridItem
import com.sentics.compose_news.presentation.common.LazyGridPager

@Composable
fun CategoryScreen(
    state: CategoryState,
    loadMore: () -> Unit
) {

    LazyGridPager(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        columns = GridCells.Adaptive(minSize = 140.dp),
        loadMore = loadMore
    ) {
        items(items = state.displayedItems) {
            ArticleGridItem(article = it, onClick = {})
        }
    }
}
