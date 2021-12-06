package dev.studiocloud.meditation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.studiocloud.meditation.data.services.response.movieResponse.MovieItem
import dev.studiocloud.meditation.data.services.viewModels.MovieViewModel
import dev.studiocloud.meditation.ui.components.PostView
import dev.studiocloud.meditation.ui.components.StoryView

class MainActivity : ComponentActivity() {
    private lateinit var movieViewModel: MovieViewModel

    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieViewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        movieViewModel.getMovies()

        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(
                darkIcons = true,
                color = Color.White,
            )

            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Image(
                        painterResource(R.drawable.ic_camera),
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
                        painterResource(R.drawable.ic_email),
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
                StoryView()
                Surface(
                    color = Color(0xFFE0E0E0),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ){}
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    itemsIndexed( movieViewModel.movies){ index, movie ->
                        if (index == movieViewModel.movies.count() - 1 && movieViewModel.page <= movieViewModel.maxPage){
                            Log.d("loadmore", "loadmore")
                            movieViewModel.getMovies()
                        }
                        PostView(item =  movie)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    PostView()
}