package com.saybit.saybitapp.presentation.navhost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.saybit.saybitapp.presentation.screen.bottomnavscreen.HomeScreen

@Composable
fun RootNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = ScreenRoute.HomeScreen.route
    ) {
        composable(ScreenRoute.HomeScreen.route) {
            HomeScreen()
        }
    }
}
