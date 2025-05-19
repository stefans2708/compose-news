package com.sentics.compose_news

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import com.loc.newsapp.ui.theme.NewsAppTheme
import com.sentics.compose_news.domain.usecase.ReadAppEntry
import com.sentics.compose_news.presentation.navigation.NavGraph
import com.sentics.compose_news.presentation.onboarding.OnboardingScreen
import com.sentics.compose_news.presentation.onboarding.OnboardingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.navigationCondition
            }
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
               NavGraph(startDestination = viewModel.startDestination)
            }
        }
    }
}
