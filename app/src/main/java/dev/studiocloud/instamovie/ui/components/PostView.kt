package dev.studiocloud.instamovie.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.services.response.movieResponse.MovieItem


@Composable
fun PostView(item: MovieItem){
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
                    text = item.title,
                    fontSize = 14.sp
                )
                Text(
                    text = item.releaseDate,
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
        Image(
            painter = rememberImagePainter(
                data = BuildConfig.IMAGE_BASE_URL+"w342/"+item.posterPath,
                builder = {
                    crossfade(true)
                },
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
            Image(
                painterResource(R.drawable.ic_love),
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
            Surface(modifier = Modifier
                .width(0.dp)
                .weight(1f)
            ){}
            Image(
                painterResource(R.drawable.ic_bookmark),
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
        }
        Text(
            text = item.overview,
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 14.dp)
                .padding(bottom = 14.dp)
        )
    }
}