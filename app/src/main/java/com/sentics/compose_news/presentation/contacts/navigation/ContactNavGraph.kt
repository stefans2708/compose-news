package com.sentics.compose_news.presentation.contacts.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sentics.compose_news.presentation.contacts.book.ContactBookScreen
import com.sentics.compose_news.presentation.contacts.book.ContactBookViewModel
import com.sentics.compose_news.presentation.navigation.Route

@Composable
fun ContactNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { paddingValues ->
        val bottomPadding = paddingValues.calculateBottomPadding()
        val topPadding = paddingValues.calculateTopPadding()

        NavHost(
            navController = navController,
            startDestination = Route.ContactBookScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding, top = topPadding)
        ) {
            composable(route = Route.ContactBookScreen.route) {
                val viewModel: ContactBookViewModel = hiltViewModel()
                ContactBookScreen()
            }
        }
    }
}
