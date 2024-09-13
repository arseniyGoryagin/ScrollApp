package com.socialmediascrollapp.scrolling.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Comment (
    val text : String,
    val user : User
)