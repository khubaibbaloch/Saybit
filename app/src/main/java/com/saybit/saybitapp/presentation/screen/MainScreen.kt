package com.saybit.saybitapp.presentation.screen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saybit.saybitapp.presentation.components.bottombar.BottomAppBar
import com.saybit.saybitapp.presentation.components.topbar.CustomMainScreenTopBar
import com.saybit.saybitapp.presentation.components.drawer.DrawerContentUi
import com.saybit.saybitapp.presentation.components.fab.FABSection
import com.saybit.saybitapp.presentation.components.topbar.TweetDetailTopBar
import com.saybit.saybitapp.presentation.navhost.RootNavHost
import com.saybit.saybitapp.presentation.navhost.ScreenRoute
import kotlinx.coroutines.launch


@Composable
fun MainScreen() {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    var showSupportSection = remember { mutableStateOf(false) }
    val rotateDownArrowIcon by animateFloatAsState(
        targetValue = if (showSupportSection.value) -180f else 0f
    )
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedIndex = remember { mutableStateOf(0) }

    val isMainFabClicked = remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val isScrollingUp by listState.isScrollingUp()
    val fullScreenRoutes = listOf(ScreenRoute.TweetDetailScreen.route)
    val showSystemBars = currentRoute !in fullScreenRoutes

    ModalNavigationDrawer(
        // modifier = Modifier.statusBarsPadding(),
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            DrawerContentUi(
                scrollState = scrollState,
                rotateDownArrowIcon = rotateDownArrowIcon,
                showSupportSection = showSupportSection,
                scope = scope
            )
        }
    ) {
        Scaffold(
            topBar = {
                when (currentRoute) {
                    ScreenRoute.HomeScreen.route -> {
                        CustomMainScreenTopBar(onProfileClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        })
                    }

                    ScreenRoute.SearchScreen.route -> CustomMainScreenTopBar(onProfileClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    })

                    ScreenRoute.TweetDetailScreen.route -> {
                        TweetDetailTopBar()
                    }

                    else -> null
                }
            },
            bottomBar = {
                if (showSystemBars) {
                    BottomAppBar(
                        selectedIndex = selectedIndex,
                        navController = navController
                    )
                }

            },
            floatingActionButton = {
                if (showSystemBars) {
                    FABSection(isMainFabClicked)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .navigationBarsPadding()
                    .background(Color.Black)
            ) {
                RootNavHost(navController, listState)
            }
        }
    }
}

@Composable
fun LazyListState.isScrollingUp(): State<Boolean> {
    return produceState(initialValue = true) {
        var lastIndex = 0
        var lastScroll = Int.MAX_VALUE
        snapshotFlow {
            firstVisibleItemIndex to firstVisibleItemScrollOffset
        }.collect { (currentIndex, currentScroll) ->
            if (currentIndex != lastIndex || currentScroll != lastScroll) {
                value = currentIndex < lastIndex ||
                        (currentIndex == lastIndex && currentScroll < lastScroll)
                lastIndex = currentIndex
                lastScroll = currentScroll
            }
        }
    }
}