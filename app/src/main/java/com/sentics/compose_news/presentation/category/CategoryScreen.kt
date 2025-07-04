package com.sentics.compose_news.presentation.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sentics.compose_news.R
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.common.ArticleGridItem
import com.sentics.compose_news.presentation.common.LazyGridPager
import com.sentics.compose_news.presentation.common.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    state: CategoryState,
    navigateToDetails: (ArticleView) -> Unit,
    onSearchTextChange: (String) -> Unit,
    onBottomSheetTrigger: () -> Unit,
    onSortingCriteriaSelect: (String) -> Unit,
    onLanguageSelect: (String) -> Unit,
    onSourceSelect: (String) -> Unit,
    onAllSourcesTrigger: () -> Unit,
    onDateRangeSelect: (Pair<String?, String?>) -> Unit,
    onSearch: () -> Unit,
    loadMore: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimen.PaddingMedium1),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                color = colorResource(R.color.text_title)
            )
            Icon(
                modifier = Modifier.clickable(onClick = onBottomSheetTrigger),
                painter = painterResource(R.drawable.ic_preferences),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

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
                ArticleGridItem(article = it, onClick = { navigateToDetails(it) })
            }
        }

        if (state.bottomSheetVisible) {
            ModalBottomSheet(
                onDismissRequest = onBottomSheetTrigger
            ) {
                CategorySettingsSheet(
                    state = state.bottomSheetState,
                    onSortingCriteriaSelect = onSortingCriteriaSelect,
                    onLanguageSelect = onLanguageSelect,
                    onSourceSelect = onSourceSelect,
                    onAllSourcesTrigger = onAllSourcesTrigger,
                    onDateRangeSelect = onDateRangeSelect
                )
            }
        }
    }
}
