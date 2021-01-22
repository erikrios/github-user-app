package io.erikrios.github.githubuserapp.repositories

import io.erikrios.github.githubuserapp.services.ServiceBuilder
import io.erikrios.github.githubuserapp.services.UserService

class UserRepository {

    private val userService = ServiceBuilder.buildService(UserService::class.java)

    suspend fun searchUsers(keyword: String) = userService.searchUsers(keyword)

    suspend fun getUserDetails(username: String) = userService.getUserDetails(username)

    suspend fun getFollowers(username: String) = userService.getFollowers(username)

    suspend fun getFollowing(username: String) = userService.getFollowing(username)
}