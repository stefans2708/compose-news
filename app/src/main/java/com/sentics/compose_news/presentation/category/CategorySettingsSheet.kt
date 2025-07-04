package com.sentics.compose_news.presentation.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.common.DateRangePickerDialog

@Composable
fun CategorySettingsSheet(
    modifier: Modifier = Modifier,
    onSortingCriteriaSelect: (String) -> Unit,
    onLanguageSelect: (String) -> Unit,
    onSourceSelect: (String) -> Unit,
    onAllSourcesTrigger: () -> Unit,
    onDateRangeSelect: (Pair<String?, String?>) -> Unit,
    state: CategorySettingsState
) {
    var showSortingOptions by remember { mutableStateOf(false) }
    var showDateRangePicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(Dimen.PaddingMedium1)
    ) {
        Text(
            text = "Language",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        FlowRow(modifier = Modifier.wrapContentHeight()) {
            state.availableLanguages.forEach { language ->
                FilterChip(
                    modifier = Modifier.padding(end = Dimen.PaddingExtraSmall),
                    label = {
                        Text(
                            text = language,
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                    leadingIcon = {
                        if (language == state.language) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        } else null
                    },
                    selected = language == state.language,
                    onClick = { onLanguageSelect.invoke(language) }
                )
            }
        }

        Spacer(modifier = Modifier.height(Dimen.PaddingSmall))
        Column(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.tertiaryContainer.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(corner = CornerSize(6.dp))
                )
                .padding(Dimen.PaddingSmall)
                .clickable { showDateRangePicker = true }
        ) {
            Text(
                text = "Date",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(Dimen.PaddingSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "From:",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = state.dateFrom.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                )
            }
            Spacer(modifier = Modifier.height(Dimen.PaddingSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "To:",
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    text = state.dateTo.orEmpty(),
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                )
            }
        }
        if (showDateRangePicker) {
            DateRangePickerDialog(
                onDateRangeSelected = onDateRangeSelect,
                onDismiss = { showDateRangePicker = false }
            )
        }

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showSortingOptions = true },
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sort by",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(Dimen.PaddingExtraSmall),
                    text = state.sortingCriteria,
                    style = MaterialTheme.typography.titleMedium,
                )
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null
                )
                DropdownMenu(
                    expanded = showSortingOptions,
                    onDismissRequest = {
                        showSortingOptions = false
                    }
                ) {
                    state.availableSortingCriteria.forEach { criteria ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = criteria,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            },
                            onClick = {
                                onSortingCriteriaSelect(criteria)
                                showSortingOptions = false
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showSortingOptions = true },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Sources",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.clickable(onClick = onAllSourcesTrigger),
                text = "All",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        FlowRow(modifier = Modifier.wrapContentHeight()) {
            availableSources.forEach { source ->
                val selected = source in state.sources

                FilterChip(
                    modifier = Modifier.padding(end = Dimen.PaddingExtraSmall),
                    label = {
                        Text(
                            text = source,
                            style = MaterialTheme.typography.labelSmall
                        )
                    },
                    leadingIcon = {
                        if (selected) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        } else null
                    },
                    selected = selected,
                    onClick = { onSourceSelect(source) }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CategorySettingsSheetPreview() {
    NewsAppTheme {
        CategorySettingsSheet(
            state = CategorySettingsState(),
            onSortingCriteriaSelect = {},
            onLanguageSelect = {},
            onSourceSelect = {},
            onAllSourcesTrigger = {},
            onDateRangeSelect = {},
        )
    }
}