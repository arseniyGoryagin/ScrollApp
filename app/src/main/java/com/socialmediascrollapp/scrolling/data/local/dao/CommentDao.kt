package com.socialmediascrollapp.scrolling.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.socialmediascrollapp.scrolling.domain.model.Post

@Dao
interface CommentDao {

    @Query("SELECT * FROM posts_table")
    fun getComments() : PagingSource<Int, Post>

    @Insert
    fun insertComments(posts : List<Post>)

    @Query("Delete from posts_table")
    fun clearAll()

}