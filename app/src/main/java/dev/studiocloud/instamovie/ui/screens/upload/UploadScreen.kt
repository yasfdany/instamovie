package dev.studiocloud.instamovie.ui.screens.upload

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.accompanist.insets.navigationBarsPadding
import dev.studiocloud.instamovie.R
import dev.studiocloud.instamovie.ui.components.GradientBox
import dev.studiocloud.instamovie.ui.components.TextArea
import dev.studiocloud.instamovie.ui.theme.Purple200
import java.io.File

@ExperimentalMaterialApi
@Composable
fun UploadScreen(
    navController: NavHostController,
    bitmapPath: String,
){
    var captionValue by remember { mutableStateOf("") }
    val file = remember { File(bitmapPath) }

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .navigationBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                rememberImagePainter(file),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .testTag("upload_image")
            )
            GradientBox(
                isReversed = true,
                modifier = Modifier.height(200.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                TextArea(
                    hint = "Input caption here...",
                    value = captionValue,
                    onValueChange = {
                        captionValue = it
                    },
                    radiusCorner = 32.dp,
                    backgroundColor = Color.White,
                    modifier = Modifier
                        .width(0.dp)
                        .weight(1f)
                )
                Surface(
                    color = Purple200,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = rememberRipple(bounded = false),
                            onClick = {
                                navController.popBackStack()
                            }
                         )
                ) {
                    Image(
                        painterResource(R.drawable.ic_round_send),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(14.dp)
                            .size(24.dp)
                    )
                }
            }
        }
    }
}