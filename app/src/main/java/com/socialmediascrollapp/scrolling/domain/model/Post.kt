package com.socialmediascrollapp.scrolling.domain.model

import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import kotlinx.serialization.Serializable


@Serializable
data class Post (
    val user: User,
    val likes : Long,
    val postId : Long,
    val caption : String,
    val imgUrl : String,
    val liked : Boolean = false
){

    companion object{

        fun toEntity(post : Post) : PostEntity{


            return PostEntity(
                user = post.user,
                likes = post.likes,
                postId = post.postId,
                caption = post.caption,
                imgUrl = post.imgUrl
            )


        }


    }
}