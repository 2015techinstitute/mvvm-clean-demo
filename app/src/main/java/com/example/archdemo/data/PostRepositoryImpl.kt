package com.example.archdemo.data

import com.example.archdemo.network.RetrofitService
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) :
    PostRepository {

    override suspend fun getPosts(): Resource<List<PostDataFromApi>> {
        return try {
            val response = retrofitService.getAllPosts()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.Success(it)
                } ?: Resource.Failure(errorCode = 0, "An unknown error occurred")
            } else {
                Resource.Failure(errorCode = 1, "An unknown error occurred")
            }
        } catch (e: Exception) {
            Resource.Failure(errorCode = 2, "An unknown error occurred")
        }
    }
}