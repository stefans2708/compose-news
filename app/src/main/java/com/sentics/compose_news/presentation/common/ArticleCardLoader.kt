package com.sentics.compose_news.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.util.shimmerEffect

@Composable
fun ArticleCardLoader(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(Dimen.ImageItem)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = Dimen.PaddingExtraSmall)
                .height(Dimen.ImageItem)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.typography.bodyMedium.lineHeight.value.dp)
                    .padding(horizontal = Dimen.PaddingMedium1)
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(MaterialTheme.typography.labelMedium.lineHeight.value.dp)
                    .padding(horizontal = Dimen.PaddingMedium1)
                    .shimmerEffect()
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ArticleCardLoaderPreview() {
    NewsAppTheme {
        ArticleCardLoader()
    }
}