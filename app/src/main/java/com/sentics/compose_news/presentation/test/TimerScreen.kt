package com.sentics.compose_news.presentation.test

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.Dimen
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun TimerScreen() {

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        TimerLaunchedEffect()

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))
        TimerCoroutineScope()

        Spacer(modifier = Modifier.height(Dimen.PaddingMedium1))
        TimerSideEffect()
    }
}

@Composable
fun TimerLaunchedEffect() {
    var timerRunning by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(0) }

    LaunchedEffect(timerRunning) {
        while (timerRunning) {
            delay(1000)
            timer++
        }
    }

    Column {
        Text(text = timer.toString())
        Button(onClick = {
            timerRunning = !timerRunning
        }) {
            Text(if (timerRunning) "Stop timer" else "Start timer")
        }
    }
}

@Composable
fun TimerCoroutineScope() {
    val scope = rememberCoroutineScope()
    var timerRunning by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(0) }
    var job: Job? by remember { mutableStateOf(null) }

    Column {
        Text(text = timer.toString())
        Button(onClick = {
            timerRunning = !timerRunning
            job?.cancel()

            if (timerRunning) {
                job = scope.launch {
                    while (true) {
                        delay(1000)
                        timer++
                    }
                }
            }
        }) {
            Text(if (timerRunning) "Stop timerrr" else "Start timerrr")
        }
    }
}


@Composable
fun TimerSideEffect() {
    var timer by remember { mutableIntStateOf(0) }

    Column {
        Text(text = timer.toString())
    }

    SideEffect {
        Thread.sleep(1000)
        timer++
        Log.d("Timer", "Side Effect, timer: $timer, sleeping: ${Thread.currentThread().name}")
    }
}

@Preview
@Composable
private fun TestScreenPreview() {
    NewsAppTheme {
        TimerScreen()
    }
}