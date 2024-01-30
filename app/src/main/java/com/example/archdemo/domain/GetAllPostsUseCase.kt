package com.example.archdemo.domain

import com.example.archdemo.data.PostDataFromApi
import com.example.archdemo.data.PostRepository
import com.example.archdemo.data.Resource
import javax.inject.Inject

class GetAllPostsUseCase @Inject constructor(private val repository: PostRepository) {
    suspend operator fun invoke(): Resource<List<PostDataFromApi>> = repository.getPosts()
}