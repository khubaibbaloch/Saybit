package com.saybit.saybitapp.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.Uri
import coil3.compose.AsyncImage
import com.saybit.saybitapp.R
import com.saybit.saybitapp.presentation.components.tweet.IconTextRow
import com.saybit.saybitapp.presentation.components.tweet.MainSection
import com.saybit.saybitapp.presentation.components.tweet.ProfileSection
import com.saybit.saybitapp.presentation.components.tweet.TweetSummary
import com.saybit.saybitapp.ui.theme.AppPrimaryColor
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.navigation.NavController

@Composable
fun TweetDetailScreen(navController: NavController) {
    val name = "khubaib aziz khan"
    val username = "@khubaibazizkhan"

    val imageUrls = listOf(
        "https://images.unsplash.com/photo-1581291518857-4e27b48ff24e",
        "https://images.unsplash.com/photo-1593642634367-d91a135587b5",
        "https://images.unsplash.com/photo-1607746882042-944635dfe10e",
        "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d"
    )
    val textFieldValue = remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()


//    MANUAL CONTROL FOR ANIMATION ETC
//    val imeInsets = WindowInsets.ime
//    val density = LocalDensity.current
//    val imeHeight by remember {
//        derivedStateOf {
//            imeInsets.getBottom(density).toFloat()
//        }
//    }

    val focusManager = LocalFocusManager.current

    // BackHandler intercepts system back press
    BackHandler(enabled = isFocused) {
        // If text field is focused, clear focus to hide keyboard
        textFieldValue.value = ""
        focusManager.clearFocus()
        // Do NOT navigate back here, just close keyboard first
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                // Profile Section
                ProfileSection(username, name)
                // MainSection
                MainSection(imageUrls)
            }
            HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
            ElevatedButton(
                onClick = {},
                contentPadding = PaddingValues(8.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                ),
            ) {
                Text(text = "Most relevant replies")
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
            }
            Column(modifier = Modifier.padding(8.dp)) {
                repeat(20) {
                    CommentsSection()
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .imePadding()
                .padding(bottom = 4.dp)
                .padding(horizontal = 8.dp)
                .drawBehind {
                    val strokeWidth = 1.dp.toPx()
                    drawLine(
                        color = Color.White.copy(0.2f),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = strokeWidth
                    )
                },
            verticalArrangement = Arrangement.Bottom,
        ) {
            Spacer(Modifier.padding(vertical = 4.dp))

            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Replying to ")
                }
                withStyle(style = SpanStyle(color = AppPrimaryColor)) {
                    append(username)
                }
            },
                fontSize = 14.sp)

            Spacer(Modifier.padding(vertical = 4.dp))

            BasicTextField(
                value = textFieldValue.value,
                onValueChange = { textFieldValue.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 16.sp
                ),
                cursorBrush = SolidColor(Color.White),
                interactionSource = interactionSource,
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .drawBehind(onDraw = {
                                val strokeWidth = 1.dp.toPx()
                                val y = size.height
                                val x = size.width
                                drawLine(
                                    color = if (isFocused) AppPrimaryColor else Color.White.copy(
                                        0.2f
                                    ),
                                    start = Offset(0f, y),
                                    end = Offset(x, y),
                                    strokeWidth = strokeWidth
                                )
                            })
                            .padding(0.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            if (textFieldValue.value.isEmpty()) {
                                Text(
                                    "post your reply",
                                    color = Color.Gray
                                )
                            }
                            innerTextField()
                        }
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(20.dp)
                        ) {
                            Icon(
                                painter = painterResource(if (isFocused) R.drawable.ic_expand else R.drawable.ic_camera),
                                contentDescription = null,
                                tint = AppPrimaryColor,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }


                }
            )

            Spacer(Modifier.padding(vertical = 4.dp))
            if (isFocused) {
                // Under Text Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceBetween
                ) {
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_photos),
                            contentDescription = null,
                            tint = AppPrimaryColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_gif),
                            contentDescription = null,
                            tint = AppPrimaryColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_list),
                            contentDescription = null,
                            tint = AppPrimaryColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    IconButton(
                        onClick = {}, modifier = Modifier
                            .size(30.dp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_location),
                            contentDescription = null,
                            tint = AppPrimaryColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    CircularProgressIndicator(modifier = Modifier.size(25.dp))
                    TextButton(
                        onClick = {},
                        modifier = Modifier.height(30.dp).width(70.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.textButtonColors(containerColor =AppPrimaryColor),
                    ) {
                        Text(text = "Reply", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun CommentsSection() {
    val name = "khubaib aziz khan"
    val username = "@khubaibazizkhan"
    val tagList = listOf("@khubaibazizkhan", "@aliimran", "@king")
    Row() {
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
                        withStyle(
                            style = SpanStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        ) {
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
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Replaying to")
                }
                withStyle(style = SpanStyle(color = AppPrimaryColor)) {
                    tagList.forEachIndexed { index, tag ->
                        when {
                            index == tagList.lastIndex -> append(" and $tag")
                            else -> append(" $tag")
                        }
                    }
                }
            })

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
                IconTextRow(painter = painterResource(R.drawable.ic_comment), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_retwitte), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_fav_outline), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_views), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_save), text = "")
                IconTextRow(painter = painterResource(R.drawable.ic_share), text = "")
            }
        }
    }
}
