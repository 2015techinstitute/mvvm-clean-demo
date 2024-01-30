package com.example.archdemo.data

interface PostRepository {
    suspend fun getPosts(): Resource<List<PostDataFromApi>>
}
