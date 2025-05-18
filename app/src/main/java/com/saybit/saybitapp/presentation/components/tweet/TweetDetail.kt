package com.saybit.saybitapp.presentation.components.tweet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.saybit.saybitapp.R

@Composable
fun ProfileSection(username: String, name: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.profile_topbar),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .padding(end = 8.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp)
        ) {
            Text(
                text = name,
                lineHeight = 1.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                text = username,
                lineHeight = 1.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        ElevatedButton(
            onClick = { /* Handle follow action */ },
            modifier = Modifier
                .height(25.dp)
                .width(90.dp),
            contentPadding = PaddingValues(0.dp),
            shape = CircleShape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 2.dp)
        ) {
            Text(
                text = "Follow",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            painter = painterResource(R.drawable.ic_morevert),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier
                .padding(start = 8.dp)
                .size(16.dp)
        )
    }
}

@Composable
fun MainSection(imageUrls: List<String>) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        TweetText()
        Spacer(modifier = Modifier.height(8.dp))

        when (imageUrls.size) {
            1 -> OneImageLayout(imageUrls)
            2 -> TwoImageLayout(imageUrls)
            3 -> ThreeImageLayout(imageUrls)
            4 -> FourImageLayout(imageUrls)
            else -> {} // No image
        }
        TweetDateAndViews()
        EngagementStats()
        BookmarkStats()
        ActionIcons()
    }
}

@Composable
fun TweetText() {
    Text(
        text = "Khubaib Aziz Khan adas d as das d as d as das d asd as d as d as d as d as d as d as d as dasd",
        fontSize = 16.sp,
        lineHeight = 20.sp,
        color = Color.White.copy(0.8f),
        modifier = Modifier.padding(end = 8.dp)
    )
}

@Composable
fun OneImageLayout(imageUrls: List<String>) {
    AsyncImage(
        model = imageUrls[0],
        contentDescription = null,
        modifier = Modifier.clip(RoundedCornerShape(12.dp))
    )
}

@Composable
fun TwoImageLayout(imageUrls: List<String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 0..1) {
            AsyncImage(
                model = imageUrls[i],
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .height(180.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = if (i == 0) 12.dp else 0.dp,
                            topEnd = if (i == 1) 12.dp else 0.dp,
                            bottomStart = if (i == 0) 12.dp else 0.dp,
                            bottomEnd = if (i == 1) 12.dp else 0.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ThreeImageLayout(imageUrls: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        AsyncImage(
            model = imageUrls[0],
            contentDescription = null,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            AsyncImage(
                model = imageUrls[1],
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
            AsyncImage(
                model = imageUrls[2],
                contentDescription = null,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomEnd = 12.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun FourImageLayout(imageUrls: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        for (i in 0..1) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (j in 0..1) {
                    val index = i * 2 + j
                    AsyncImage(
                        model = imageUrls[index],
                        contentDescription = null,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clip(
                                RoundedCornerShape(
                                    topStart = if (index == 0) 12.dp else 0.dp,
                                    topEnd = if (index == 1) 12.dp else 0.dp,
                                    bottomStart = if (index == 2) 12.dp else 0.dp,
                                    bottomEnd = if (index == 3) 12.dp else 0.dp
                                )
                            ),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@Composable
fun TweetDateAndViews() {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        Text(text = "7:18 AM • 11 May 25 • ", color = Color.Gray)

        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.White)) {
                append(text = "3.9M")
            }
            withStyle(style = SpanStyle(color = Color.Gray)) {
                append(text = "Views")
            }
        })
    }
}

@Composable
fun EngagementStats() {
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
        StatText("2,333", "Reposts")
        Spacer(modifier = Modifier.width(8.dp))
        StatText("2,333", "Quotes")
        Spacer(modifier = Modifier.width(8.dp))
        StatText("2,333", "Likes")
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
}

@Composable
fun StatText(value: String, label: String) {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle()) {
            append(value)
        }
        append(" ")
        withStyle(style = SpanStyle(color = Color.Gray)) {
            append(label)
        }
    })
}
@Composable
fun BookmarkStats() {
    Column {
        StatText("2,333", "Bookmarks")
    }
    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
}
@Composable
fun ActionIcons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionIcon(R.drawable.ic_comment)
        ActionIcon(R.drawable.ic_retwitte)
        ActionIcon(R.drawable.ic_fav_outline)
        ActionIcon(R.drawable.bookmark, size = 17.dp)
        ActionIcon(R.drawable.ic_share)
    }
}

@Composable
fun ActionIcon(iconRes: Int, size: Dp = 20.dp) {
    IconButton(onClick = {}, modifier = Modifier.size(35.dp)) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.size(size)
        )
    }
}
