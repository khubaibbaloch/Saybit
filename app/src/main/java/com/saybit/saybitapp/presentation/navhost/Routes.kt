package com.saybit.saybitapp.presentation.navhost

sealed class ScreenRoute(val route: String) {
    data object MainScreen : ScreenRoute(route = "MainScreenRoute")
    data object HomeScreen : ScreenRoute(route = "HomeScreenRoute")
    data object SearchScreen : ScreenRoute(route = "SearchScreenRoute")
    data object AiScreen : ScreenRoute(route = "AiScreenRoute")
    data object CommunitiesScreen : ScreenRoute(route = "CommunitiesScreenRoute")
    data object NotificationScreen : ScreenRoute(route = "NotificationScreenRoute")
    data object InboxScreen : ScreenRoute(route = "InboxScreenRoute")
}