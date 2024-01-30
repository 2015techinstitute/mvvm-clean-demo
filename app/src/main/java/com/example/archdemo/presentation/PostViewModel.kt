package com.example.archdemo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archdemo.data.PostUiState
import com.example.archdemo.data.Resource
import com.example.archdemo.domain.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getAllPostsUseCase: GetAllPostsUseCase): ViewModel() {

    private val _posts =  MutableLiveData<List<PostUiState>>()

    val posts : LiveData<List<PostUiState>>
        get() = _posts

    fun getPostsActual() {
        val postUiStateList = mutableListOf<PostUiState>()
        viewModelScope.launch {
            when (val result = getAllPostsUseCase.invoke()) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    result.response.forEach {
                        postUiStateList.add(
                            PostUiState(
                                userId = it.userId,
                                id = it.id,
                                title = it.title,
                                body = it.body
                            )
                        )
                    }
                    _posts.postValue(postUiStateList)
                }
                is Resource.Failure -> {}

            }
        }
    }

}