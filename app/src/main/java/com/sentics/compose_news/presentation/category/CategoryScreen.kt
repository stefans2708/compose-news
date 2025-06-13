package com.sentics.compose_news.presentation.category

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.sentics.compose_news.presentation.common.ArticleCard

@Composable
fun CategoryScreen(
    state: CategoryState,
    loadMore: () -> Unit
) {
    val listState = rememberLazyListState()
    val shouldLoadMore by remember(state) {
        derivedStateOf {
            listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index?.let { lastVisible ->
                Log.d("SENTA123", "last visible: $lastVisible, ${state.displayedItems.size}")
                lastVisible > state.displayedItems.size - 10
            } == true
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = listState
    ) {
        items(items = state.displayedItems) {
            ArticleCard(article = it.toArticle(), onClick = {})
        }
    }

    LaunchedEffect(shouldLoadMore) {
        Log.d("SENTA123", "CategoryScreen: triggered")
        if (shouldLoadMore) loadMore()
    }
}
