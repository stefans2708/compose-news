package com.sentics.compose_news.presentation.contacts.book

import MockedData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.Dimen
import com.sentics.compose_news.presentation.contacts.data.Contact

@Composable
fun ContactItem(
    contact: Contact,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Dimen.PaddingExtraSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box(
            modifier =
                Modifier
                    .background(color = Color.LightGray, shape = CircleShape)
                    .width(56.dp)
                    .height(56.dp)
                    .padding(Dimen.PaddingExtraSmall)
        ) {
            Text(
                text = contact.initials,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(align = Alignment.Center),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        Text(
            text = contact.fullName,
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = Dimen.PaddingMedium1)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ContactItemPreview() {
    NewsAppTheme {
        ContactItem(
            contact = MockedData.randomContact
        )
    }
}