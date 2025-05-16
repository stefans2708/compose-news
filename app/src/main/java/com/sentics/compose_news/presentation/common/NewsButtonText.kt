package com.sentics.compose_news.presentation.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.ui.theme.WhiteGray

@Composable
fun NewsButtonText(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = WhiteGray
        )
    }
}

@Preview
@Composable
private fun NewsButtonPreview() {
    NewsAppTheme {
        NewsButtonText(text = "Button") { }
    }
}
