package com.socialmediascrollapp.scrolling.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.socialmediascrollapp.scrolling.data.local.dao.PostDao
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.util.converters.Converters


@Database(entities = [PostEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Db : RoomDatabase(){


    abstract fun postsDao() : PostDao


    companion object{


        private var INSTANCE : Db? = null

        fun getDb(context : Context) : Db{


            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(

                    context = context.applicationContext,
                    Db::class.java,
                    "Db"

                ).build()

                INSTANCE = instance
                instance
            }

        }



    }


}