package com.example.archdemo.data

import com.example.archdemo.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val retrofitService: RetrofitService) : PostRepository {

    override suspend fun getPosts(): Response<List<PostDataFromApi>> {
        return withContext(Dispatchers.IO){
            retrofitService.getAllPosts()
        }
    }
}