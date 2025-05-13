@file:OptIn(ExperimentalMaterial3Api::class)

package com.saybit.saybitapp.presentation.components.topbar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.R


@Composable
fun TweetDetailTopBar() {
    TopAppBar(
        title = { Text(text = "Post", modifier = Modifier.padding(16.dp)) },
        navigationIcon = {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
        },
        actions = {
            Icon(
                painter = painterResource(R.drawable.icon_ai_outline),
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(25.dp)
            )
        },
        modifier = Modifier.drawBehind {
            val strokeWidth = 1.dp.toPx()
            drawLine(
                color = Color.White.copy(0.2f),
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black),
    )
}