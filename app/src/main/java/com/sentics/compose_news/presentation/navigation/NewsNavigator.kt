package com.sentics.compose_news.presentation.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.sentics.compose_news.R
import com.sentics.compose_news.presentation.bookmark.BookmarkScreen
import com.sentics.compose_news.presentation.bookmark.BookmarkViewModel
import com.sentics.compose_news.presentation.category.ArticleView
import com.sentics.compose_news.presentation.category.CategoryScreen
import com.sentics.compose_news.presentation.category.CategoryViewModel
import com.sentics.compose_news.presentation.details.DetailsEvent
import com.sentics.compose_news.presentation.details.DetailsScreen
import com.sentics.compose_news.presentation.details.DetailsViewModel
import com.sentics.compose_news.presentation.home.HomeScreen
import com.sentics.compose_news.presentation.home.HomeViewModel
import com.sentics.compose_news.presentation.navigation.component.BottomNavigationItem
import com.sentics.compose_news.presentation.navigation.component.NewsBottomNavigation
import com.sentics.compose_news.presentation.search.SearchScreen
import com.sentics.compose_news.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
            BottomNavigationItem(icon = R.drawable.ic_preferences, text = "Categories"),
        )
    }
    var selectedItem = rememberSaveable(backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            Route.CategoryScreen.route -> 3
            else -> 0
        }
    }
    val isBottomBarVisible = remember(backStackState) {
        backStackState?.destination?.route in setOf(
            Route.HomeScreen.route,
            Route.SearchScreen.route,
            Route.BookmarkScreen.route,
            Route.CategoryScreen.route
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                NewsBottomNavigation(
                    items = bottomNavigationItems,
                    selected = selectedItem,
                    onItemSelected = { index ->
                        when (index) {
                            0 -> navigateToTab(navController = navController, route = Route.HomeScreen.route)
                            1 -> navigateToTab(navController = navController, route = Route.SearchScreen.route)
                            2 -> navigateToTab(navController = navController, route = Route.BookmarkScreen.route)
                            3 -> navigateToTab(navController = navController, route = Route.CategoryScreen.route)
                        }
                    }
                )
            }
        },
    ) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToSearch = { navigateToTab(navController, Route.SearchScreen.route) },
                    navigateToDetails = { article -> navigateToDetails(navController, article) },
                )
            }

            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { navigateToDetails(navController, it) }
                )
            }

            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.messageSideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.messageSideEffect, Toast.LENGTH_SHORT).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<ArticleView?>("article")?.let { article ->
                    DetailsScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = navController::navigateUp
                    )
                }
            }

            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value

                BookmarkScreen(
                    state = state,
                    navigateToDetails = { navigateToDetails(navController, it) }
                )
            }

            composable(route = Route.CategoryScreen.route) {
                val viewModel: CategoryViewModel = hiltViewModel()
                val state by viewModel.state.collectAsStateWithLifecycle()

                CategoryScreen(
                    state = state,
                    onSearchTextChange = viewModel::onSearchQueryChange,
                    onSearch = viewModel::searchArticles,
                    loadMore = viewModel::loadPage
                )
            }
        }
    }
}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) {
                saveState = true
            }
        }
        restoreState = true
        launchSingleTop = true
    }
}

private fun navigateToDetails(navController: NavController, article: ArticleView) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(Route.DetailsScreen.route)
}