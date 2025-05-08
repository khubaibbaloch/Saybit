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
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import com.saybit.saybitapp.presentation.components.CustomMainScreenTopBar
import com.saybit.saybitapp.presentation.components.DrawerContentUi
import com.saybit.saybitapp.presentation.components.DrawerItem
import com.saybit.saybitapp.presentation.navhost.RootNavHost
import com.saybit.saybitapp.presentation.navhost.ScreenRoute
import com.saybit.saybitapp.ui.theme.AppPrimaryColor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    ModalNavigationDrawer(
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
                    ScreenRoute.HomeScreen.route -> CustomMainScreenTopBar(onProfileClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    })

                    ScreenRoute.SearchScreen.route -> CustomMainScreenTopBar(onProfileClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    })

                    else -> null
                }
            },
            bottomBar = {
                BottomAppBar(
                    selectedIndex = selectedIndex,
                    navController = navController
                )
            },
            floatingActionButton = {
                Row(
                    modifier = Modifier
                        .height(200.dp)
                    // .background(Color.Red)

                ) {

                    if (isMainFabClicked.value) {
                        Column(
                            modifier = Modifier.height(190.dp),
                            verticalArrangement = Arrangement.SpaceAround,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text("Go Live", color = Color.White)
                            Text("Spaces", color = Color.White)
                            Text("Photos", color = Color.White)
                            Text("Post", color = Color.White)


                        }
                    }

                    Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        BouncySmallFab(
                            isClicked = isMainFabClicked.value,
                            painter = painterResource(R.drawable.ic_golive),
                            onClick = {})
                        BouncySmallFab(
                            isClicked = isMainFabClicked.value,
                            painter = painterResource(R.drawable.ic_spaces),
                            onClick = {})
                        BouncySmallFab(
                            isClicked = isMainFabClicked.value,
                            painter = painterResource(R.drawable.ic_photos),
                            onClick = {})
                        BouncyLargeFab(
                            onClick = {
                                isMainFabClicked.value = !isMainFabClicked.value
                            },
                            isIconChange = isMainFabClicked.value
                        )
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            containerColor = Color(0xFF1DA1F2),
//                            modifier = Modifier.size(40.dp)
//                        ) {
//
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            containerColor = Color(0xFF1DA1F2),
//                            modifier = Modifier.size(55.dp)
//                        ) {
//
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }


                    }
//                    Row() {
//                        Text("Go Live", color = Color.White)
//
//                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            containerColor = Color(0xFF1DA1F2),
//                            modifier = Modifier.size(40.dp)
//                        ) {
//
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }
//
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 16.dp))
//                    Row {
//                        Text("Go Live", color = Color.White)
//
//                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            modifier = Modifier.size(40.dp),
//                            containerColor = Color(0xFF1DA1F2)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.padding(top = 16.dp))
//                    Row {
//                        Text("Go Live", color = Color.White)
//
//                        Spacer(modifier = Modifier.padding(horizontal = 16.dp))
//
//
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            modifier = Modifier.size(40.dp),
//                            containerColor = Color(0xFF1DA1F2)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }
//                    }
//                    Spacer(modifier = Modifier.padding(top = 16.dp))
//                    Row {
//                        Text("Go Live", color = Color.White)
//
//                        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
//
//
//                        FloatingActionButton(
//                            onClick = {},
//                            shape = CircleShape,
//                            modifier = Modifier.size(55.dp),
//                            containerColor = Color(0xFF1DA1F2)
//                        ) {
//                            Icon(
//                                painter = painterResource(id = R.drawable.dark),  // Use your icon here
//                                contentDescription = "Add",
//                                tint = Color.White,
//                                modifier = Modifier.size(20.dp)
//                            )
//                        }
//                    }
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
                RootNavHost(navController)
            }
        }
    }


}

@Composable
fun BouncyLargeFab(onClick: () -> Unit, isIconChange: Boolean) {
    val scale = remember { Animatable(1f) }
    val icon =
        if (isIconChange) painterResource(R.drawable.ic_wing) else painterResource(R.drawable.ic_add)
    val scope = rememberCoroutineScope()

    val rotateSpec = tween<Float>(
        durationMillis = 200,
        easing = LinearEasing
    )

    val animateWingIcon = animateFloatAsState(
        targetValue = if (isIconChange) 0f else -80f,
        animationSpec = rotateSpec
    )

    val animateAddIcon = animateFloatAsState(
        targetValue = if (isIconChange) 90f else 0f,
        animationSpec = rotateSpec
    )

    val animateIcon = if (isIconChange) animateWingIcon else animateAddIcon
    FloatingActionButton(
        onClick = {
            scope.launch {
                scale.animateTo(
                    targetValue = 1.1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
                scale.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
            }
            onClick()
        },
        shape = CircleShape,
        containerColor = AppPrimaryColor,
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .size(55.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = "Add",
            tint = Color.White,
            modifier = Modifier
                .size(25.dp)
                .rotate(animateIcon.value)
        )

    }
}

@Composable
fun BouncySmallFab(isClicked: Boolean, painter: Painter, onClick: () -> Unit) {
    val scale by animateFloatAsState(
        targetValue = if (isClicked) 1f else 0f,
        animationSpec = tween(
            durationMillis = 100,
            easing = EaseInOut
        ), label = ""
    )


    FloatingActionButton(
        onClick = { onClick() },
        shape = CircleShape,
        containerColor = Color.White,
        modifier = Modifier
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale,
                transformOrigin = TransformOrigin(0.5f, 0.5f)
            )
            .size(40.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = "Add",
            tint = AppPrimaryColor,
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                )
        )
    }
}
