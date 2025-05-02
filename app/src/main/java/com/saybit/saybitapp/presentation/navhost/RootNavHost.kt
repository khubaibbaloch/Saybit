package com.saybit.saybitapp.presentation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.AiScreen
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.CommunitiesScreen
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.HomeScreen
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.InboxScreen
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.NotificationScreen
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.SearchScreen

@Composable
fun RootNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.HomeScreen.route
    ) {
        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen()
        }
        composable(ScreenRoute.SearchScreen.route) {
            SearchScreen()
        }
        composable(ScreenRoute.AiScreen.route) {
            AiScreen()
        }
        composable(ScreenRoute.CommunitiesScreen.route) {
            CommunitiesScreen()
        }
        composable(ScreenRoute.NotificationScreen.route) {
            NotificationScreen()
        }
        composable(ScreenRoute.InboxScreen.route) {
            InboxScreen()
        }
    }
}
