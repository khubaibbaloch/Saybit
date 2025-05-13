package com.saybit.saybitapp.presentation.components.drawer

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp

@Composable
fun  DrawerItem(label: String, icon: Painter, iconSize:Dp,onClick:()-> Unit){
    NavigationDrawerItem(
        label = { Text(text = label) },
        icon = {
            Icon(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(iconSize)
            )
        },
        selected = false,
        onClick = {onClick }
    )
}