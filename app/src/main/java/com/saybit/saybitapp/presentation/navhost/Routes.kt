package com.saybit.saybitapp.presentation.navhost

sealed class ScreenRoute(val route: String) {
    data object MainScreen : ScreenRoute(route = "MainScreenRoute")
    data object HomeScreen : ScreenRoute(route = "HomeScreenRoute")
}