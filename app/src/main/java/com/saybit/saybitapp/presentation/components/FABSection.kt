package com.saybit.saybitapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.R

@Composable
fun FABSection(isMainFabClicked: MutableState<Boolean>){
    Row(
        modifier = Modifier
            .height(200.dp)
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

        }
    }
}