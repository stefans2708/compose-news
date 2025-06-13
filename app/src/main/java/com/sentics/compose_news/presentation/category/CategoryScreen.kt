package com.sentics.compose_news.presentation.category

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sentics.compose_news.presentation.common.ArticleCard
import com.sentics.compose_news.presentation.common.LazyColumnPager

@Composable
fun CategoryScreen(
    state: CategoryState,
    loadMore: () -> Unit
) {
    LazyColumnPager (
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        loadMore = loadMore
    ) {
        items(items = state.displayedItems) {
            ArticleCard(article = it.toArticle(), onClick = {})
        }
    }
}
