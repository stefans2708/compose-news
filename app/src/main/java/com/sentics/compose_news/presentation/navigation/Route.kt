package com.sentics.compose_news.presentation.navigation

sealed class Route(
    val route: String
) {
    data object OnboardingScreen : Route(route = "onboarding")
    data object HomeScreen : Route(route = "home")
    data object SearchScreen : Route(route = "search")
    data object BookmarkScreen : Route(route = "bookmark")
    data object DetailsScreen : Route(route = "details")
    data object AppStartNavigation : Route(route = "appStartNavigation")
    data object NewsNavigation : Route(route = "newsNavigation")
    data object NewsNavigatorScreen : Route(route = "newsNavigator")
    data object CategoryScreen : Route(route = "category")

    data object ContactBookScreen : Route(route = "contactBook")
}