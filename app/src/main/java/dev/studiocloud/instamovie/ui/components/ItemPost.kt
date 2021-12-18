package dev.studiocloud.instamovie.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import java.util.*
import kotlin.concurrent.schedule


@ExperimentalAnimationApi
@Composable
fun ItemPost(
    item: MovieItem,
    onTapPost: (item: MovieItem) -> Unit,
){
    var loved by remember { mutableStateOf(item.loved) }
    var saved by remember { mutableStateOf(item.saved) }
    var loveVisibility by remember {(mutableStateOf(false))}

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(BuildConfig.IMAGE_BASE_URL+"w342/"+item.backdropPath),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .width(0.dp)
                    .weight(1f)
            ) {
                Text(
                    text = item.title ?: "",
                    fontSize = 14.sp
                )
                Text(
                    text = item.releaseDate ?: "",
                    fontSize = 12.sp,
                    color = Color(0xFFA2A2A2)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.ic_option),
                contentDescription = null,
                modifier = Modifier.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    onClick = {
                    },
                )
            )
        }
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = rememberImagePainter(
                    data = BuildConfig.IMAGE_BASE_URL+"w500/"+item.posterPath,
                    builder = {
                        crossfade(true)
                    },
                ),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onDoubleTap = {
                                loved = true
                                item.loved = true

                                loveVisibility = !loveVisibility
                                Timer().schedule(800) {
                                    loveVisibility = !loveVisibility
                                }
                            },
                            onTap = {
                                onTapPost(item)
                            },
                        )
                    }

            )
            androidx.compose.animation.AnimatedVisibility(
                visible = loveVisibility,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_love_filled),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = Color.White),
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painterResource(if (loved) R.drawable.ic_love_filled else R.drawable.ic_love),
                contentDescription = null,
                colorFilter = ColorFilter.tint(if (loved) Color.Red else Color.Black),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            loved = !loved
                            item.loved = !item.loved
                        },
                    )
                    .padding(14.dp)
            )
            Image(
                painterResource(R.drawable.ic_comment),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        },
                    )
                    .padding(
                        vertical = 14.dp,
                    )
            )
            Image(
                painterResource(R.drawable.ic_share_filled),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {

                        },
                    )
                    .padding(14.dp)
            )
            Spacer(modifier = Modifier
                .width(0.dp)
                .weight(1f)
            )
            Image(
                painterResource(if (saved) R.drawable.ic_bookmark_filled else R.drawable.ic_bookmark),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false),
                        onClick = {
                            saved = !saved
                            item.saved = !item.saved
                        },
                    )
                    .padding(14.dp)
            )
        }
        Text(
            text = item.overview ?: "",
            fontSize = 12.sp,
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(bottom = 14.dp)
        )
    }
}