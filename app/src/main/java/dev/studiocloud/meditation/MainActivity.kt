package dev.studiocloud.meditation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    items(5){
                        postView()
                    }
                }
            }
        }
    }
}

@Composable
fun postView(){
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
                painter = painterResource(R.drawable.dog),
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
                    text = "Lanasmith",
                    fontSize = 14.sp
                )
                Text(
                    text = "Manhattan, NYC",
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
            painter = painterResource(id = R.drawable.dog),
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
                painterResource(R.drawable.ic_share),
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
            text = "yasfdany",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
        Text(
            text = "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before the final copy is",
            fontSize = 12.sp,
            modifier = Modifier.padding(horizontal = 14.dp)
                .padding(bottom = 14.dp)
        )
    }
}

@Composable
fun StoryView(){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 14.dp,
            ),
        contentPadding = PaddingValues(horizontal = 14.dp)
    ) {
        items(12){
            Surface(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .border(color = Color.Magenta, width = 2.dp, shape = CircleShape)
            ) {
                Image(
                    painter = painterResource(R.drawable.dog),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .size(54.dp)
                        .clip(CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    postView()
}