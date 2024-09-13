package com.socialmediascrollapp.scrolling.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.domain.model.Post
import com.socialmediascrollapp.scrolling.presentation.components.CircleLoading
import com.socialmediascrollapp.scrolling.presentation.components.Post


@Composable
fun  ScrollScreen(viewmodel: ScrollViewModel = hiltViewModel()){


    val posts = viewmodel.postsFlow.collectAsLazyPagingItems()

    ScrollContent(posts)

}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  ScrollContent(posts : LazyPagingItems<PostEntity>){



    Scaffold(

        topBar = {

            TopAppBar(title = { Text(text = "Social Media Scroll App") }, navigationIcon = {})

        },
        content = { padding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                if (posts.loadState.refresh is LoadState.Loading) {
                    CircleLoading()
                } else if (posts.loadState.refresh is LoadState.Error) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Text(
                            text = "Error ${(posts.loadState.refresh as LoadState.Error).error}",
                            modifier = Modifier.align(alignment = Alignment.Center),
                            textAlign = TextAlign.Center,
                            color = Color.LightGray
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        items(count = posts.itemCount) { index ->

                            val post = posts[index]

                            post?.let {
                                Post(
                                    caption = post.caption,
                                    img = post.imgUrl,
                                    likes = post.likes,
                                    name = post.user.name,
                                    username = post.user.username,
                                    liked = false,
                                    onCommentClick = { /* handle comment click */ },
                                    onLikeClick = { /* handle like click */ }
                                )
                            }
                        }


                        item {
                            if (posts.loadState.append is LoadState.Loading) {
                                CircleLoading()
                            }
                        }


                    }
                }
            }
        }

    )





}
