package com.example.archdemo.data

import retrofit2.Response

interface PostRepository {
    suspend fun getPosts(): Response<List<PostDataFromApi>>
}
