package com.example.archdemo.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.archdemo.data.FakePostRepositoryImpl
import com.example.archdemo.data.PostUiState
import com.example.archdemo.data.Resource
import com.example.archdemo.domain.GetAllPostsUseCase
import com.example.archdemo.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostViewModelTest {

    private lateinit var repository: FakePostRepositoryImpl
    private lateinit var postViewModel: PostViewModel
    private lateinit var getAllPostsUseCase: GetAllPostsUseCase

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    @Before
    fun setUp() {
        repository = FakePostRepositoryImpl()
        getAllPostsUseCase = GetAllPostsUseCase(repository)
        postViewModel = PostViewModel(getAllPostsUseCase)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPostsActualTest() = runTest {
        val expected = mutableListOf<PostUiState>()
        when(val result = getAllPostsUseCase.invoke()) {
            is Resource.Success -> {
                result.response.forEach {
                    expected.add(
                        PostUiState(
                            userId = it.userId,
                            id = it.id,
                            title = it.title,
                            body = it.body
                        )
                    )
                }
            }
            is Resource.Failure -> {}
            Resource.Loading -> {}
        }
        val actual = mutableListOf<PostUiState>()
        postViewModel.getPostsActual()
        actual.addAll(postViewModel.posts.getOrAwaitValueTest())
        Assert.assertEquals(expected, actual)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getPostsFailureTest() = runTest {
        var expected : String? = ""
        repository.setShouldReturnNetworkError(true)
        val result = getAllPostsUseCase.invoke()
        expected = when(result) {
            is Resource.Success -> {""}
            is Resource.Failure -> { result.errorMessage }
            Resource.Loading -> {""}
        }
        val actual = "An unknown error occurred"
        postViewModel.getPostsActual()
        Assert.assertEquals(expected, actual)
    }


}