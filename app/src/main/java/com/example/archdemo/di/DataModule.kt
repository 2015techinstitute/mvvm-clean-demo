package com.example.archdemo.di

import com.example.archdemo.data.PostRepository
import com.example.archdemo.data.PostRepositoryImpl
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
    fun bindsPostsRepository(postRepository: PostRepositoryImpl): PostRepository = postRepository
}
