package com.saybit.saybitapp.presentation.components.fab

import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.ui.theme.AppPrimaryColor


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
