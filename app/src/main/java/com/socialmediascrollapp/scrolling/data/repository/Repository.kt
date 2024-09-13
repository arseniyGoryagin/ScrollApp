package com.socialmediascrollapp.scrolling.data.repository

import arrow.core.Either
import com.socialmediascrollapp.network.ApiService
import com.socialmediascrollapp.scrolling.data.local.Db
import com.socialmediascrollapp.scrolling.domain.model.Comment
import com.socialmediascrollapp.scrolling.domain.model.Post
import com.socialmediascrollapp.scrolling.domain.repository.PostsRepository
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val db : Db) : PostsRepository {


        private val postsDao = db.postsDao()

    override suspend fun getPosts(limit: Int): Either<Exception, List<Post>> {
       return  Either.catch {
            apiService.getPosts(limit = 10)
        }.mapLeft { it as Exception }
    }



    override suspend fun clearAllPosts() {
        postsDao.clearAll()
    }

    override suspend  fun cachePosts(posts: List<Post>) {
        val postEntities = posts.map { post ->
            Post.toEntity(post)
        }
       postsDao.insertPosts(postEntities)
    }


}