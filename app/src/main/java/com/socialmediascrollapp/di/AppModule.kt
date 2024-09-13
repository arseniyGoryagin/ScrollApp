package com.socialmediascrollapp.di

import android.content.Context
import android.graphics.pdf.PdfDocument.Page
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.socialmediascrollapp.network.ApiService
import com.socialmediascrollapp.scrolling.data.local.Db
import com.socialmediascrollapp.scrolling.data.local.dao.PostDao
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.data.repository.Repository
import com.socialmediascrollapp.scrolling.data.source.PostRemoteMediator
import com.socialmediascrollapp.scrolling.domain.model.Post
import com.socialmediascrollapp.scrolling.domain.repository.PostsRepository
import com.socialmediascrollapp.util.constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context : Context) : Db{
        return Db.getDb(context)
    }

    @Provides
    @Singleton
    fun providePostDao(db : Db) : PostDao{
        return db.postsDao()
    }


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesPostPager(repository: PostsRepository, postsDao: PostDao) : Pager<Int, PostEntity> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = PostRemoteMediator(repository),
            pagingSourceFactory = {
                postsDao.getPosts()
            }
        )
    }


    @Provides
    @Singleton
    fun providesRepository(api : ApiService, db : Db) : PostsRepository{
        return Repository(api, db)
    }

    @Provides
    @Singleton
    fun providesApi() : ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(
                Json { ignoreUnknownKeys = false}.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(ApiService::class.java)
    }


}