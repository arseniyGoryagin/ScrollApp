package com.socialmediascrollapp.scrolling.data.source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.domain.repository.PostsRepository

@OptIn(ExperimentalPagingApi::class)
class PostRemoteMediator(private val repository: PostsRepository) : RemoteMediator<Int, PostEntity>(){


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PostEntity>
    ): MediatorResult {


        println("STATE  =  " + loadType)

        if(loadType == LoadType.PREPEND){
            return MediatorResult.Success(endOfPaginationReached = false)
        }


        return repository.getPosts(10).fold(
            { error ->
                MediatorResult.Error(error)
            },
            { result ->

                if(loadType == LoadType.REFRESH){
                    repository.clearAllPosts()
                }


                repository.cachePosts(result)

                return  MediatorResult.Success(endOfPaginationReached = false)
            })


    }


}