package com.example.archdemo.data

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<out T>(val response: T) : Resource<T>()
    data class Failure(val errorCode: Int?, val errorMessage: String?) : Resource<Nothing>()
}