package com.saybit.saybitapp.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.saybit.saybitapp.R
import com.saybit.saybitapp.presentation.navhost.ScreenRoute

@Composable
fun BottomAppBar(selectedIndex: MutableState<Int>,navController: NavController){
    val filledIcons = listOf(
        R.drawable.icon_home_filled,
        R.drawable.icon_search_filled,
        R.drawable.icon_ai_filled,
        R.drawable.icon_bell_filled,
        R.drawable.icon_user_filled,
        R.drawable.icon_mail_filled
    )

    val outlineIcons = listOf(
        R.drawable.icon_home_outline,
        R.drawable.icon_search_outline,
        R.drawable.icon_ai_outline,
        R.drawable.icon_bell_outline,
        R.drawable.icon_user_outline,
        R.drawable.icon_mail_outline
    )
    val screenList = listOf(
        ScreenRoute.HomeScreen.route,
        ScreenRoute.SearchScreen.route,
        ScreenRoute.AiScreen.route,
        ScreenRoute.CommunitiesScreen.route,
        ScreenRoute.NotificationScreen.route,
        ScreenRoute.InboxScreen.route
    )


    NavigationBar(
        modifier = Modifier
            .height(55.dp)
            .drawBehind {
                drawLine(
                    color = Color.White.copy(0.2f),
                    start = Offset(0f, 0f),
                    end = Offset(size.width, 0f),
                    strokeWidth = 1.dp.toPx()
                )
            },
        containerColor = Color.Black,
    ) {
        filledIcons.indices.forEach { index ->
            val iconRes =
                if (selectedIndex.value == index) filledIcons[index] else outlineIcons[index]

            NavigationBarItem(
                selected = selectedIndex.value == index,
                onClick = {
                    selectedIndex.value = index
                    navController.navigate(screenList[index]) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(iconRes),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
            )
        }
    }
}