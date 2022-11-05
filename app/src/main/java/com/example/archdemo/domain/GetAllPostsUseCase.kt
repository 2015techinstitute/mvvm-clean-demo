package com.example.archdemo.domain

import com.example.archdemo.data.PostDataFromApi
import com.example.archdemo.data.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val repository: PostRepository) {

    private var posts : List<PostDataFromApi> = mutableListOf()

    suspend operator fun invoke(): List<PostDataFromApi> =
        withContext(Dispatchers.Default){
            val result = repository.getPosts()
            posts = result.body() as List<PostDataFromApi>
            posts
        }
}