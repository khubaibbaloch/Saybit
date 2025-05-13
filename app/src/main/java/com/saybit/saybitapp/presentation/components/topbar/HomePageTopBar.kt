package com.saybit.saybitapp.presentation.components.topbar

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Black
        ),
    )
}
