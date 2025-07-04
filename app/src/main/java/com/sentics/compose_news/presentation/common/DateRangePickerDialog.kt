package com.sentics.compose_news.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.Dimen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateRangePickerDialog(
    modifier: Modifier = Modifier,
    onDateRangeSelected: (Pair<String?, String?>) -> Unit,
    onDismiss: () -> Unit
) {
    val state = rememberDateRangePickerState()

    DatePickerDialog(
        modifier = modifier,
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateRangeSelected.invoke(
                    Pair(
                        state.selectedStartDateMillis.toDateString(),
                        state.selectedEndDateMillis.toDateString()
                    )
                )
                onDismiss()
            }) {
                Text(
                    text = "Ok",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Close",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    ) {
        DateRangePicker(
            state,
            showModeToggle = true,
            title = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}

private fun Long?.toDateString(): String? =
    this?.let { date -> SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(date)) }


@Preview
@Composable
private fun DateRangePickerDialogPreview() {
    NewsAppTheme {
        DateRangePickerDialog(
            onDateRangeSelected = {},
            onDismiss = {}
        )
    }
}
