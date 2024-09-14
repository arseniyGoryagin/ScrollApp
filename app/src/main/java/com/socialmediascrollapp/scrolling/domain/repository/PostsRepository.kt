package com.socialmediascrollapp.scrolling.domain.repository

import arrow.core.Either
import com.socialmediascrollapp.scrolling.domain.model.Comment
import com.socialmediascrollapp.scrolling.domain.model.Post


interface PostsRepository {

    suspend  fun getPosts(limit : Int) : Either< Exception, List<Post>>

    suspend fun clearAllPosts()

    suspend fun cachePosts(posts : List<Post>)

    suspend fun changeLike(postId: Int, value : Boolean)
}