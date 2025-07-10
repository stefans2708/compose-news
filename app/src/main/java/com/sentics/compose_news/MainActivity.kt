package com.sentics.compose_news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.presentation.contacts.navigation.ContactNavGraph
import com.sentics.compose_news.presentation.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.navigationCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                ContactNavGraph()
            }
        }
    }
}
