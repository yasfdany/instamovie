package dev.studiocloud.instamovie.ui.screens.home.pages

import android.os.CountDownTimer
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import dev.studiocloud.instamovie.BuildConfig
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.data.remote.response.tvResponse.TvItem
import dev.studiocloud.instamovie.data.viewModels.TvViewModel
import dev.studiocloud.instamovie.ui.components.Line
import dev.studiocloud.instamovie.ui.components.TextArea
import dev.studiocloud.instamovie.ui.theme.blackText14

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun TvList(
    navController: NavHostController? = null,
    tvViewModel: TvViewModel? = null,
){
    val scrollState = rememberLazyListState()
    var visibleSearch by remember { mutableStateOf(true) }
    var searchValue by remember { mutableStateOf("") }
    val timer = object: CountDownTimer(500, 1000) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            tvViewModel?.searchTv(
                reset = true,
                search = searchValue,
            )
        }
    }

    visibleSearch = scrollState.isScrollingUp()

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            AnimatedVisibility(visible = visibleSearch) {
                TextArea(
                    value = searchValue,
                    onValueChange = {
                        searchValue = it
                        tvViewModel?.loading?.value = true;

                        timer.cancel()
                        timer.start()
                    },
                    hint = "Search here...",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    trailingIcon = {
                        if (searchValue.isNotEmpty()){
                            Image(
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = "Close Button",
                                modifier = Modifier
                                    .width(24.dp)
                                    .height(24.dp)
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false),
                                        onClick = {
                                            searchValue = ""
                                            tvViewModel?.searchTv(
                                                reset = true,
                                                search = searchValue,
                                            )
                                        },
                                    )
                            )

                        }
                    }
                )
            }
            Line()
            if(tvViewModel?.loading?.value!!){
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp)
                    )
                    Text(
                        text = "Memuat data...",
                        style = blackText14(),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }
            } else {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3),
                    state = scrollState,
                ) {
                    itemsIndexed(tvViewModel.tvExplore) { index, tv ->
                        if (index == tvViewModel.tvExplore.count() - 2
                            && tvViewModel.page < tvViewModel.maxPage
                            && !tvViewModel.loading.value
                        ){
                            tvViewModel.loading.value = true
                            tvViewModel.searchTv(
                                search = searchValue,
                            )
                        }

                        ItemTv(tv)
                    }
                }

            }
        }
    }
}

@Composable
fun ItemTv(tvItem: TvItem){
    val configuration = LocalConfiguration.current

    Image(
        painter = rememberImagePainter(
            data = BuildConfig.IMAGE_BASE_URL+"w500/"+tvItem.posterPath,
            builder = {
                crossfade(true)
            },
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .background(color = Color(0xFFF1F1F1))
            .height(configuration.screenWidthDp.dp / 3)
            .border(
                width = 1.dp,
                color = Color.White,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
                onClick = {
                },
            )
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Preview(){
    TvList()
}