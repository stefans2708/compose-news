package com.sentics.compose_news.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.presentation.Dimen

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onItemClick: (Article) -> Unit
) {
    if (articles.isEmpty()) {
        EmptyScreen()
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimen.PaddingMedium1),
            contentPadding = PaddingValues(all = Dimen.PaddingExtraSmall2)
        ) {
            items(count = articles.size) {
                articles[it].let { article ->
                    ArticleCard(article = article, onClick = { onItemClick(article) })
                }
            }
        }
    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onItemClick: (Article) -> Unit
) {
    val pageLoadSuccess = handlePagingResult(articles)

    if (pageLoadSuccess) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(Dimen.PaddingMedium1),
            contentPadding = PaddingValues(all = Dimen.PaddingExtraSmall2)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article, onClick = { onItemClick(article) })
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    articlesPage: LazyPagingItems<Article>
): Boolean {
    val loadState = articlesPage.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        articlesPage.itemCount == 0 -> {
            EmptyScreen()
            false
        }

        else -> true
    }
}

@Composable
fun ShimmerEffect(modifier: Modifier = Modifier) {
    Column(verticalArrangement = Arrangement.spacedBy(Dimen.PaddingMedium1)) {
        repeat(10) {
            ArticleCardLoader(modifier = modifier.padding(horizontal = Dimen.PaddingMedium1))
        }
    }
}