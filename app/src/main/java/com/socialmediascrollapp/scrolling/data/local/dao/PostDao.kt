package com.socialmediascrollapp.scrolling.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.domain.model.Post


@Dao
interface PostDao {

    @Query("SELECT * FROM posts_table")
    fun getPosts() : PagingSource<Int,PostEntity>

    @Insert
    suspend fun insertPosts(posts : List<PostEntity>)

    @Query("Delete from posts_table")
     suspend  fun clearAll() : Int

    @Query("Update posts_table set liked = :value where id = :id")
    suspend  fun updateLike (id : Int, value : Boolean)

}