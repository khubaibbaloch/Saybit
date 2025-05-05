package com.saybit.saybitapp.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DrawerContentUi(
    scrollState: ScrollState,
    rotateDownArrowIcon: Float,
    showSupportSection: MutableState<Boolean>,
    scope: CoroutineScope
) {
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
                    showSupportSection.value = !showSupportSection.value
                    scope.launch {
                        delay(100)
                        scrollState.animateScrollTo(scrollState.maxValue)
                    }
                }
            )
            AnimatedVisibility(
                visible = showSupportSection.value,
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
