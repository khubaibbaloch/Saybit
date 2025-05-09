package com.saybit.saybitapp.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.R
import com.saybit.saybitapp.ui.theme.AppPrimaryColor
import kotlinx.coroutines.launch

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
