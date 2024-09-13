package com.socialmediascrollapp.util.converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.socialmediascrollapp.scrolling.domain.model.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromUser(user : User) : String{
        return Json.encodeToString(user)
    }


    @TypeConverter
    fun toUser(userJson : String) : User{
        return Json.decodeFromString(userJson)
    }




}