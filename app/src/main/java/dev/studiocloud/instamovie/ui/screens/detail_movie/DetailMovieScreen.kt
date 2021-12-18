package dev.studiocloud.instamovie.ui.screens.detail_movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.ui.components.GradientBox
import dev.studiocloud.instamovie.ui.theme.Purple200
import dev.studiocloud.instamovie.ui.theme.blackText12
import dev.studiocloud.instamovie.ui.theme.blackText18
import dev.studiocloud.instamovie.ui.theme.whiteText12

@Composable
fun DetailMovieScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
) {
    val movieDetail : MovieDetailData? = movieViewModel.movieDetail.value
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = false,
    )

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxHeight()
    ) {
        Column {
            Box {
                Image(
                    painter = rememberImagePainter(
                        BuildConfig.IMAGE_BASE_URL+"w342/"+ movieDetail?.backdropPath,
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    Surface(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                RoundedCornerShape(
                                    topEnd = 24.dp,
                                    topStart = 24.dp
                                )
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(14.dp)
                        ) {
                            Text(
                                text = movieDetail?.title ?: "",
                                style = blackText18(FontWeight.Medium),
                            )
                            Surface(
                                color = Purple200,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(
                                        12.dp
                                    ))
                            ) {
                                Text(
                                    text = movieDetail?.voteAverage.toString(),
                                    modifier = Modifier.padding(
                                        vertical = 4.dp,
                                        horizontal = 8.dp,
                                    ),
                                    style = whiteText12()
                                )
                            }
                        }
                    }
                }
                GradientBox()
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White) ,
                    modifier = Modifier
                        .padding(
                            vertical = 38.dp,
                            horizontal = 14.dp
                        )
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {
                                navController.popBackStack()
                            },
                        )
                        .width(32.dp)
                        .height(32.dp)
                )
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 14.dp)
            ) {
                itemsIndexed(movieDetail?.genres ?: listOf()) { index, genre ->
                    Surface(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .border(
                                color = Color.Magenta,
                                width = 1.dp,
                                shape = RoundedCornerShape(
                                    12.dp
                                ),
                            )
                    ) {
                        Text(
                            text = genre.name ?: "",
                            modifier = Modifier.padding(
                                vertical = 4.dp,
                                horizontal = 8.dp,
                            ),
                            style = blackText12().copy(color = Color.Magenta)
                        )
                    }
                }
            }
        }
    }
}