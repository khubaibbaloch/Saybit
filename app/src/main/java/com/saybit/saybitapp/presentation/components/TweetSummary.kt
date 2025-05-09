package com.saybit.saybitapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.saybit.saybitapp.R
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun TweetSummary() {
    val name = "khubaib aziz khan"
    val username = "@khubaibazizkhan"

    Row(modifier = Modifier.padding(8.dp)) {
        Icon(
            painter = painterResource(R.drawable.profile_topbar),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold)) {
                            append(name)
                        }
                        append(" ")
                        withStyle(style = SpanStyle(fontSize = 16.sp, color = Color.Gray)) {
                            append(username)
                        }
                    },
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = "• 18h", fontSize = 14.sp, color = Color.Gray)
                    Icon(
                        painter = painterResource(R.drawable.icon_ai_outline),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Gray
                    )
                }
            }

            Text(
                text = "Khubaib Aziz Khan adas d as das d as d as das d asd as d as d as d as d as d as d as d as dasd",
                fontSize = 14.sp,
                lineHeight = 18.sp,
                color = Color.White.copy(0.8f),
                modifier = Modifier.padding(end = 8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconTextRow(painter = painterResource(R.drawable.ic_comment), text = "2.6k")
                IconTextRow(painter = painterResource(R.drawable.ic_retwitte), text = "2")
                IconTextRow(painter = painterResource(R.drawable.ic_fav_outline), text = "1.3k")
                IconTextRow(painter = painterResource(R.drawable.ic_views), text = "1.3M")
                IconTextRow(painter = painterResource(R.drawable.ic_save), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_share), text = "")
            }
        }
    }
}

@Composable
fun IconTextRow(painter: Painter, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = Color.Gray
        )
        if (text.isNotEmpty()) {
            Text(text = text, fontSize = 13.sp, color = Color.Gray)
        }
    }
}
