package com.sentics.compose_news.presentation.details

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.R
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.domain.model.Source
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.details.component.DetailsTopBar

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW)
                    .setData(article.url.toUri())
                    .takeIf { it.resolveActivity(context.packageManager) != null }
                    ?.let { context.startActivity(it) }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND)
                    .putExtra(Intent.EXTRA_TEXT, article.url)
                    .setType("text/plain")
                    .takeIf { it.resolveActivity(context.packageManager) != null }
                    ?.let { context.startActivity(it) }
            },
            onBookmarkClick = { event(DetailsEvent.SaveArticle) },
            onBackClick = navigateUp
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = Dimen.PaddingMedium1,
                    end = Dimen.PaddingMedium1,
                    top = Dimen.PaddingMedium1
                )
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context).data(article.imageUrl).build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimen.ImageFullPage)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

            Text(
                text = article.title,
                style = MaterialTheme.typography.displaySmall,
                color = colorResource(R.color.text_title)
            )

            Text(
                text = article.content,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(R.color.body)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DetailsScreenPreview() {
    NewsAppTheme {
        DetailsScreen(
            article = Article(
                author = "John Doe",
                content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                description = "OPIS",
                publishedAt = "09.06.2025",
                source = Source(id = "1", name = "bbc"),
                title = "Et harum quidem rerum facilis",
                url = "https://www.google.com/#q=explicari",
                imageUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Farticles%2F&psig=AOvVaw1rTfaKwxhaGK01LA9xygRy&ust=1749549057962000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCPDj9qKI5I0DFQAAAAAdAAAAABAL"
            ), event = {}) { }
    }
}
