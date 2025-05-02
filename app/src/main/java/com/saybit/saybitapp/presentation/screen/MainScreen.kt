package com.saybit.saybitapp.presentation.screen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saybit.saybitapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.navigation.compose.rememberNavController
import com.saybit.saybitapp.presentation.components.CustomMainScreenTopBar
import com.saybit.saybitapp.presentation.components.DrawerItem
import com.saybit.saybitapp.presentation.navhost.RootNavHost
import com.saybit.saybitapp.presentation.navhost.ScreenRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var showSupportSection by remember { mutableStateOf(false) }
    val rotateDownArrowIcon by animateFloatAsState(
        targetValue = if (showSupportSection) -180f else 0f
    )
    val navController = rememberNavController()
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                drawerContainerColor = Color.Black,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(shape = CircleShape),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))

                    Text(
                        text = "Khubaib Aziz Khan",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 16.sp // Match to fontSize to reduce vertical gap
                    )
                    Text(
                        text = "@Khubaib1230",
                        color = Color.Gray,
                        fontSize = 14.sp,
                        lineHeight = 14.sp
                    )
                    Spacer(modifier = Modifier.padding(top = 8.dp))
                    Row {
                        Text(text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontSize = 14.sp)) {
                                append("0 ")
                            }
                            withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("Following")
                            }
                        })
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White, fontSize = 14.sp)) {
                                append("0 ")
                            }
                            withStyle(style = SpanStyle(color = Color.Gray, fontSize = 14.sp)) {
                                append("Followers")
                            }
                        })
                    }
                }
                HorizontalDivider(modifier = Modifier.padding(16.dp))
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(scrollState)
                ) {
                    DrawerItem(
                        label = "Profile",
                        icon = painterResource(R.drawable.profile),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Premium",
                        icon = painterResource(R.drawable.premium),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Bookmark",
                        icon = painterResource(R.drawable.bookmark),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Jobs",
                        icon = painterResource(R.drawable.jobs),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Lists",
                        icon = painterResource(R.drawable.lists),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Spaces",
                        icon = painterResource(R.drawable.spaces),
                        iconSize = 20.dp,
                        onClick = {})
                    DrawerItem(
                        label = "Monetization",
                        icon = painterResource(R.drawable.monetization),
                        iconSize = 20.dp,
                        onClick = {})

                    HorizontalDivider(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 32.dp)
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Setting & Support") },
                        badge = {
                            Icon(
                                painter = painterResource(R.drawable.down_arrow),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .rotate(rotateDownArrowIcon)
                            )
                        },
                        selected = false,
                        onClick = {
                            showSupportSection = !showSupportSection
                            scope.launch {
                                delay(100)
                                scrollState.animateScrollTo(scrollState.maxValue)
                            }
                        }
                    )
                    AnimatedVisibility(
                        visible = showSupportSection,
                        enter = fadeIn(),
                        exit = shrinkVertically()
                    ) {
                        Column {
                            DrawerItem(
                                label = "Settings",
                                icon = painterResource(R.drawable.settings),
                                iconSize = 20.dp,
                                onClick = {})
                            DrawerItem(
                                label = "Help",
                                icon = painterResource(R.drawable.help),
                                iconSize = 20.dp,
                                onClick = {})
                        }

                    }
                }
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.dark),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                }

            }
        }
    ) {
        Scaffold(
            topBar = {
                CustomMainScreenTopBar(onProfileClick = {
                    scope.launch {
                        drawerState.apply {
                            if (isClosed) open() else close()
                        }
                    }
                })
            },
            bottomBar = {
                var selectedIndex by remember { mutableStateOf(0) }

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
                            if (selectedIndex == index) filledIcons[index] else outlineIcons[index]

                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
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
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .background(Color.Black)
            ) {
                RootNavHost(navController)
                Text("Custom Drawer")
            }
        }
    }


}