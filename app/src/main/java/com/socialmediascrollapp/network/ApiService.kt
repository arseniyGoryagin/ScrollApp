package com.socialmediascrollapp.network


import com.socialmediascrollapp.scrolling.domain.model.Comment
import com.socialmediascrollapp.scrolling.domain.model.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("posts")
    suspend fun getPosts(@Query("limit") limit: Int) : List<Post>

    @GET("comments")
    suspend fun getComments(@Query("limit") limit : Int) : List<Comment>

}