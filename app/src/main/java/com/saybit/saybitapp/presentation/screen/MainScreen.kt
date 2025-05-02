package com.saybit.saybitapp.presentation.screen


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.graphics.painter.Painter
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
import androidx.compose.ui.draw.rotate
import com.saybit.saybitapp.presentation.components.DrawerItem
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
    ModalNavigationDrawer(
        drawerState = rememberDrawerState(DrawerValue.Open),
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .padding(top = 8.dp, start = 16.dp)
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
                IconButton(onClick = {}) {
                    Icon(
                        painter = painterResource(R.drawable.dark),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )

                }
                // ...other drawer items
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("My App") }
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Text("Custom Drawer")
            }
        }
    }


}