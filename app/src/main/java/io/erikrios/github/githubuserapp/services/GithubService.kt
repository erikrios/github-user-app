package io.erikrios.github.githubuserapp.services

import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.models.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    /**
     * GET request to search a user by keyword
     */
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") keyword: String
    ): Response<UserResponse>

    /**
     * GET request to get the user details
     */
    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<User>

    /**
     * Get request to get the list of followers
     */
    @GET("users/{username}/followers")
    suspend fun getFollowers(@Path("username") username: String): Response<List<User>>

    /**
     * Get request to get the list following
     */
    @GET("users/{username}/following")
    suspend fun getFollowing(@Path("username") username: String): Response<List<User>>
}