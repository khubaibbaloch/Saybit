package com.saybit.saybitapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saybit.saybitapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomMainScreenTopBar(onProfileClick: () -> Unit) {
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { onProfileClick() }) {
                Icon(
                    painter = painterResource(R.drawable.profile_topbar),
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        title = {
            Icon(
                painter = painterResource(R.drawable.premium),
                contentDescription = "Center Icon",
                modifier = Modifier.size(20.dp)
            )
        },
        actions = {
            Text(
                text = "Upgrade",
                fontSize = 14.sp,
                color = Color.White
            )
            IconButton(onClick = { }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }
        }
    )
}
