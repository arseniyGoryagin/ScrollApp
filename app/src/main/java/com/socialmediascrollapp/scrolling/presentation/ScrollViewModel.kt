package com.socialmediascrollapp.scrolling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.socialmediascrollapp.scrolling.data.local.entities.PostEntity
import com.socialmediascrollapp.scrolling.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.sample
import javax.inject.Inject


@HiltViewModel
class ScrollViewModel @Inject constructor(
        private val repository: PostsRepository,
        private val postsPager : Pager<Int, PostEntity>
) : ViewModel() {

        val postsFlow = postsPager.flow.cachedIn(viewModelScope)
}