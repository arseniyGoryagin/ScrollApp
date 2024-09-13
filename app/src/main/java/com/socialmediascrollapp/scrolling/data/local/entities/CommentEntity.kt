package com.socialmediascrollapp.scrolling.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.socialmediascrollapp.scrolling.domain.model.User
import kotlinx.serialization.Serializable

@Entity("comments_table")
data class CommentEntity (


    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val text : String,
    val user : User
)