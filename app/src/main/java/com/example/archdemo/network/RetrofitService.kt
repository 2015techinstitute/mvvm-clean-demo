package com.example.archdemo.network

import com.example.archdemo.common.Constants
import com.example.archdemo.data.PostDataFromApi
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    /* Call current weather data by city name */
    @GET(Constants.BASE_URL + "posts")
    suspend fun getAllPosts(): Response<List<PostDataFromApi>>

}