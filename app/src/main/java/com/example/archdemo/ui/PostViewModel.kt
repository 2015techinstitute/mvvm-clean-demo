package com.example.archdemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.archdemo.data.PostDataFromApi
import com.example.archdemo.data.PostRepository
import com.example.archdemo.data.PostUiState
import com.example.archdemo.domain.GetAllPostsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val getAllPostsUseCase: GetAllPostsUseCase): ViewModel() {

    private val posts =  MutableLiveData<List<PostUiState>>()

    fun getPost() : LiveData<List<PostUiState>>{
        return posts
    }

    fun getPostsActual(){
        val postUiStateList = mutableListOf<PostUiState>()
        viewModelScope.launch{
            val result = getAllPostsUseCase.invoke()
            result.forEach { postUiStateList.add(PostUiState(userId = it.userId, id = it.id, title = it.title, body = it.body)) }
            posts.postValue(postUiStateList)
        }
    }

}