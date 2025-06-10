package com.sentics.compose_news.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.sentics.compose_news.R
import com.sentics.compose_news.domain.model.Article
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.common.ArticleList
import com.sentics.compose_news.presentation.common.SearchBar
import com.sentics.compose_news.presentation.navigation.Route

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83d\uDFE5") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimen.PaddingMedium1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimen.PaddingMedium1)
        )
        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

        SearchBar(
            modifier = Modifier.padding(horizontal = Dimen.PaddingMedium1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = navigateToSearch,
            onSearch = {}
        )

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimen.PaddingMedium1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))

        ArticleList(
            modifier = Modifier.padding(horizontal = Dimen.PaddingMedium1),
            articles = articles,
            onItemClick = navigateToDetails
        )
    }
}