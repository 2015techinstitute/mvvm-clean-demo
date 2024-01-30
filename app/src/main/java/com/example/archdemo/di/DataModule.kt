package com.example.archdemo.di

import com.example.archdemo.data.PostRepository
import com.example.archdemo.data.PostRepositoryImpl
import com.example.archdemo.domain.GetAllPostsUseCase
import com.example.archdemo.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun providesPostsRepository(retrofitService: RetrofitService): PostRepository =
        PostRepositoryImpl(retrofitService)

    @Singleton
    @Provides
    fun providesGetAllPostsUseCase(postRepository: PostRepository): GetAllPostsUseCase =
        GetAllPostsUseCase(postRepository)
}
