package com.saybit.saybitapp.presentation.screen.bottomnavscreen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.presentation.components.tweet.TweetSummary
import com.saybit.saybitapp.presentation.screen.isScrollingUp
import com.saybit.saybitapp.ui.theme.AppPrimaryColor

@Composable
fun HomeScreen(listState: LazyListState) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("ForYou", "Following")
    val isScrollingUp by listState.isScrollingUp()

    val tabRowHeight by animateDpAsState(
        targetValue = if (isScrollingUp) 56.dp else 0.dp,
        animationSpec = tween(durationMillis = 10),
        label = "TabRowHeight"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex,
            containerColor = Color.Transparent,
            modifier = Modifier
                .fillMaxWidth(),
            indicator = { tabPositions ->
                TabRowDefaults.PrimaryIndicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = AppPrimaryColor,
                    width = 150.dp
                )
            }) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.White else Color.Gray
                        )
                    })
            }
        }


        when (selectedTabIndex) {
            0 -> {
                ForYouSection(listState)
            }

            1 -> {
                FollowingSection()
            }

            else -> {
                ErrorSection()
            }
        }
    }
}

@Composable
fun ForYouSection(listState: LazyListState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(state = listState) {
            items(100) {
                TweetSummary()
                HorizontalDivider()
            }
        }

    }
}

@Composable
fun FollowingSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Following")
    }
}

@Composable
fun ErrorSection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = "Error")
    }
}
