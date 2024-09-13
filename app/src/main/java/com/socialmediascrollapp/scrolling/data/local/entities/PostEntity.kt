package com.socialmediascrollapp.scrolling.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialmediascrollapp.scrolling.domain.model.User
import kotlinx.serialization.Serializable




@Entity(tableName = "posts_table")
data class PostEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val user : User,

    val likes : Long,
    val postId : Long,
    val caption : String,
    val imgUrl : String
)
