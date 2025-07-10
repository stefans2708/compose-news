package com.sentics.compose_news.presentation.contacts.book

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
fun ContactBookScreen() {

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
                .background(color = Color.Yellow.copy(alpha = 0.4f))
        ) {

        }

        val bookState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.8f),
            state = bookState
        ) {
            items(MockedData.contacts) {
                ContactItem(it)
            }
        }

        val scope = rememberCoroutineScope()

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.1f)
                .background(color = Color.Magenta.copy(alpha = 0.4f)),
            verticalArrangement = Arrangement.SpaceEvenly,
            userScrollEnabled = false
        ) {
            items(MockedData.letterIndexMap.keys.toList()) { character ->
                Text(
                    text = character,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .clickable {
                            scope.launch {
                                bookState.scrollToItem(index = MockedData.letterIndexMap[character]!!)
                            }
                        },
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

    }
}
