package com.socialmediascrollapp.scrolling.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastCbrt
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.socialmediascrollapp.R



@Composable
fun  Post (
           caption : String,
           img : String,
           likes : Long,
           name : String,
           username : String,
           liked : Boolean,
           onCommentClick : () -> Unit,
           onLikeClick : () -> Unit){




    val likeVisible = remember {
        mutableStateOf(false)
    }



    Column {

        Row(
            modifier = Modifier.padding(start = 16.dp, top = 14.dp)
        ){
            Column(){
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold
                    )
                Spacer(modifier = Modifier.height(8 .dp))
                Text(text = username)
            }

        }
        
        
        Spacer(modifier = Modifier.height(15.dp))

        // Image

        SubcomposeAsyncImage(
            contentScale = ContentScale.FillBounds,
            loading = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ){
                    CircularProgressIndicator()
                }
            },
            error = {
                Text("Error loading img")
            },
            model = img ,
            contentDescription = null,

            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            onLikeClick()
                        }
                    )
                }

            )


        Spacer(modifier = Modifier.height(18.dp))


        // Likes and comments
        Row(
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(start = 16.dp)
        ){



                if(liked){
                    Icon(

                        Icons.Filled.Favorite,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = Modifier.clickable { onLikeClick() }
                    )
                }
                else {
                    Icon(
                        Icons.Outlined.FavoriteBorder,
                        contentDescription = null,
                        modifier = Modifier.clickable { onLikeClick()    }
                    )
                }


            Spacer(modifier = Modifier.width(16.dp))


            Icon(painter = painterResource(id = R.drawable.comment_outline), contentDescription = null,
                modifier = Modifier.clickable { onLikeClick })


        }

        Spacer(modifier = Modifier.height(16.dp))


        // Likes number
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "$likes likes",
            fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(18.dp))

        // Post text
        Text(text = buildAnnotatedString{

            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)){
                append(username)
            }
            append(" $caption")
            },
            modifier = Modifier.padding(start = 16.dp))

        Spacer(modifier = Modifier.height(4.dp))
        
        // Comments number
        Text(
            text = "View comments",
            color = Color.LightGray,
            modifier = Modifier
                .padding(start = 16.dp)
                .clickable { onCommentClick() }
            )


        Spacer(modifier = Modifier.height(19.dp))

    }



}


@Preview
@Composable
fun prev(){
    Post( caption = "Hello", img = "https://via.placeholder.com/600/771796", likes = 10000,  name = "Arseniy", username = "BugBoy", liked = true, onLikeClick = {}, onCommentClick = {} )
}