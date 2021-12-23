package dev.studiocloud.instamovie.ui.screens.detail_movie

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.remote.response.movieDetailResponse.MovieDetailData
import dev.studiocloud.instamovie.data.remote.response.movieResponse.MovieItem
import dev.studiocloud.instamovie.data.viewModels.MovieViewModel
import dev.studiocloud.instamovie.ui.components.GradientBox
import dev.studiocloud.instamovie.ui.components.ItemRow
import dev.studiocloud.instamovie.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun DetailMovieScreen(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
) {
    val historyIds = remember { mutableListOf<Int>() }
    val movieDetail: MovieDetailData? =  movieViewModel.movieDetail.value
    val similarMovies: MutableList<MovieItem> =  movieViewModel.similarMovies

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = Color.Transparent,
        darkIcons = false,
    )

    BackHandler {
        if (historyIds.isEmpty()){
            navController.popBackStack()
        } else {
            movieViewModel.getMovieDetail(historyIds.last())
            movieViewModel.getSimilarMovies(historyIds.last())
            historyIds.removeLast()
        }
    }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .navigationBarsPadding()
    ) {
        if(movieViewModel.loadingDetail.value || movieDetail != null){
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())
            ) {
                Box {
                    GlideImage(
                        BuildConfig.IMAGE_BASE_URL+"w780/"+ movieDetail?.backdropPath,
                        circularReveal = CircularReveal(duration = 800),
                        contentScale = ContentScale.Crop,
                        shimmerParams = ShimmerParams(
                            baseColor = Color.White,
                            highlightColor = Color(0xFFDBDBDB),
                            durationMillis = 1000,
                            dropOff = 0.65f,
                            tilt = 20f
                        ),
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
                                modifier = Modifier.padding(14.dp)
                            ) {
                                Text(
                                    text = movieDetail?.title ?: "",
                                    style = blackText18(FontWeight.Medium),
                                    modifier = Modifier
                                        .width(0.dp)
                                        .weight(1f)
                                )
                                ChipRating(movieDetail?.voteAverage.toString())
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
                                    if (historyIds.isEmpty()){
                                        navController.popBackStack()
                                    } else {
                                        movieViewModel.getMovieDetail(historyIds.last())
                                        movieViewModel.getSimilarMovies(historyIds.last())
                                        historyIds.removeLast()
                                    }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        ItemRow(
                            leftText = "Status",
                            rightText = movieDetail?.status ?: "",
                            modifier = Modifier.padding(
                                top = 24.dp,
                                start = 14.dp,
                            )
                        )
                        ItemRow(
                            leftText = "Runtime",
                            rightText = (movieDetail?.runtime ?: 0).toString(),
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 14.dp,
                            )
                        )
                        ItemRow(
                            leftText = "Release",
                            rightText = movieDetail?.releaseDate ?: "",
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 14.dp,
                            )
                        )
                        ItemRow(
                            leftText = "Budget",
                            rightText = "Rp. "+(movieDetail?.budget ?: 0).toString(),
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 14.dp,
                            )
                        )
                        ItemRow(
                            leftText = "Revenue",
                            rightText = "Rp. "+(movieDetail?.revenue ?: 0).toString(),
                            modifier = Modifier.padding(
                                top = 10.dp,
                                start = 14.dp,
                            )
                        )
                    }
                    GlideImage(
                        BuildConfig.IMAGE_BASE_URL+"w342/"+ movieDetail?.posterPath,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .width(120.dp)
                            .height(160.dp)
                            .padding(
                                top = 24.dp,
                                end = 14.dp,
                            )
                            .clip(RoundedCornerShape(12.dp))
                        ,
                    )
                }
                Text(
                    "Overview",
                    style = blackText18(FontWeight.Medium),
                    modifier = Modifier.padding(
                        top = 24.dp,
                        start = 14.dp,
                    )
                )
                Text(
                    movieDetail?.overview ?: "",
                    style = blackText14().copy(color = Color.Black.copy(0.4f)),
                    modifier = Modifier.padding(horizontal = 14.dp)
                )
                Text(
                    "Similar Movies",
                    style = blackText18(FontWeight.Medium),
                    modifier = Modifier.padding(
                        top = 24.dp,
                        start = 14.dp,
                    )
                )
                LazyRow(
                    contentPadding = PaddingValues(
                        top = 24.dp,
                        start = 14.dp,
                        bottom = 14.dp
                    )
                ){
                    itemsIndexed(similarMovies){ _, movie ->
                        ItemSimilarMovie(
                            movie = movie,
                            onTap = {
                                historyIds.add(movieDetail?.id!!)
                                movieViewModel.getMovieDetail(movie.id)
                                movieViewModel.getSimilarMovies(movie.id)
                            }
                        )
                    }
                }
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_warning),
                    contentDescription = "",
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                )
                Text(
                    text = "Data tidak ditemukan",
                    style = blackText16(),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }
    }
}

@Composable
fun ItemSimilarMovie(
    movie: MovieItem,
    onTap: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(
                end = 14.dp,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                onClick = {
                    onTap()
                }
            )
    ) {
        Column {
            GlideImage(
                BuildConfig.IMAGE_BASE_URL+"w342/"+ movie.posterPath,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                circularReveal = CircularReveal(),
                modifier = Modifier
                    .width(120.dp)
                    .height(160.dp)
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(12.dp),
                        clip = true,
                    )
                    .background(color = Color.White)
                ,
            )
            Text(
                text = movie.title ?: "",
                style = blackText12(),
                modifier = Modifier
                    .width(120.dp)
                    .padding(
                        top = 6.dp,
                        end = 14.dp,
                    )
            )
        }
    }
}

@Composable
fun ChipRating(
    rating: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
    ) {
        Surface(
            color = Purple200,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        12.dp
                    )
                )
        ) {
            Text(
                text = rating,
                modifier = Modifier.padding(
                    vertical = 4.dp,
                    horizontal = 8.dp,
                ),
                style = whiteText12()
            )
        }
    }
}
