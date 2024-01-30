package com.example.archdemo.data

class FakePostRepositoryImpl : PostRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = true
    }

    override suspend fun getPosts(): Resource<List<PostDataFromApi>> {
        return if(shouldReturnNetworkError){
            Resource.Failure(0, "An unknown error occurred")
        } else {
            val post1 = PostDataFromApi(1,1, "Hello", "A quick brown fox")
            val post2 = PostDataFromApi(1,2, "World", "jumped over the lazy dog")
            val list =  arrayListOf<PostDataFromApi>()
            list.add(post1)
            list.add(post2)
            Resource.Success(list)
        }
    }
}