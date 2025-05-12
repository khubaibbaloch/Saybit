package com.saybit.saybitapp.presentation.screen


import android.graphics.drawable.Icon
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saybit.saybitapp.presentation.components.BottomAppBar
import com.saybit.saybitapp.presentation.components.BouncyLargeFab
import com.saybit.saybitapp.presentation.components.BouncySmallFab
import com.saybit.saybitapp.presentation.components.CustomMainScreenTopBar
import com.saybit.saybitapp.presentation.components.DrawerContentUi
import com.saybit.saybitapp.presentation.components.DrawerItem
import com.saybit.saybitapp.presentation.components.FABSection
import com.saybit.saybitapp.presentation.navhost.RootNavHost
import com.saybit.saybitapp.presentation.navhost.ScreenRoute
import com.saybit.saybitapp.ui.theme.AppPrimaryColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min


@OptIn(ExperimentalMaterial3Api::class)
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
                        TopAppBar(title = { Text(text = "Post") }, navigationIcon = {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        }, actions = {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        })
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