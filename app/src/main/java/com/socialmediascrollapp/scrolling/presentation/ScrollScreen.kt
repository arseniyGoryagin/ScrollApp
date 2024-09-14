package com.socialmediascrollapp.scrolling.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import arrow.core.constant
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.domain.model.Post
import com.socialmediascrollapp.scrolling.presentation.components.CircleLoading
import com.socialmediascrollapp.scrolling.presentation.components.ErrorText
import com.socialmediascrollapp.scrolling.presentation.components.Post
import kotlinx.coroutines.launch


@Composable
fun  ScrollScreen(viewmodel: ScrollViewModel = hiltViewModel()){


    val posts = viewmodel.postsFlow.collectAsLazyPagingItems()


    val onLikeClick = { postId : Int , value : Boolean ->
        viewmodel.updateLike(postId, value)
    }

    val onCommentClick = {postId : Long  ->

    }

    ScrollContent(posts, onLikeClick = onLikeClick, onCommentClick = onCommentClick)

}



@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun  ScrollContent(posts : LazyPagingItems<PostEntity>, onLikeClick : (Int, Boolean) -> Unit, onCommentClick : (Long) -> Unit){



    val pullRefreshState = rememberPullRefreshState(
        refreshing = posts.loadState.refresh is LoadState.Loading ,
      onRefresh = { posts.refresh()}
    )


    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()



    val onCommentClick = { postId : Long ->
        onCommentClick(postId)
    }

    Scaffold(
        topBar = {
            Surface(
                shadowElevation = 3.dp
            ) {
                TopAppBar(
                    title = { Text(text = "Social Media Scroll App") },
                    navigationIcon = {},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = Color.White,
                onClick = {
                    scope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                content = {
                    Icon(
                        Icons.Filled.KeyboardArrowUp, 
                        contentDescription = null,
                        tint = Color.Black
                        )

                }
            )
        },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    //.windowInsetsBottomHeight(WindowInsets.navigationBars),
            )
            {
                if (posts.loadState.refresh is LoadState.Loading) {
                    CircleLoading()
                } else if (posts.loadState.refresh is LoadState.Error) {
                    ErrorText((posts.loadState.refresh as LoadState.Error).error.localizedMessage)
                } else {

                    LazyColumn(
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState),
                    ) {
                        items(count = posts.itemCount) { index ->

                            val post = posts[index]




                            post?.let {


                                val liked = remember { mutableStateOf(post.liked) }


                                Post(
                                    caption = post.caption,
                                    img = post.imgUrl,
                                    likes = post.likes,
                                    name = post.user.name,
                                    username = post.user.username,
                                    liked = liked.value,
                                    onCommentClick = { onCommentClick(post.postId) },
                                    onLikeClick = {
                                        liked.value = !liked.value
                                        onLikeClick(post.id, !post.liked)
                                    }
                                )
                            }
                        }


                        item {
                            if (posts.loadState.append is LoadState.Loading) {
                                CircleLoading()
                            } else if (posts.loadState.refresh is LoadState.Error) {
                                ErrorText((posts.loadState.refresh as LoadState.Error).error.localizedMessage)
                            }
                        }

                    }


                }

                PullRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    refreshing = posts.loadState.refresh is LoadState.Loading,
                    state = pullRefreshState
                )



            }
        }

    )

}

