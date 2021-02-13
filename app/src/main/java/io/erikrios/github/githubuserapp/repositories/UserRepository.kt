package io.erikrios.github.githubuserapp.repositories

import io.erikrios.github.githubuserapp.databases.UserDatabase
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.services.ServiceBuilder
import io.erikrios.github.githubuserapp.services.UserService

class UserRepository(private val db: UserDatabase? = null) {

    private val userService = ServiceBuilder.buildService(UserService::class.java)

    suspend fun searchUsers(keyword: String) = userService.searchUsers(keyword)

    suspend fun getUserDetails(username: String) = userService.getUserDetails(username)

    suspend fun getFollowers(username: String) = userService.getFollowers(username)

    suspend fun getFollowing(username: String) = userService.getFollowing(username)

    suspend fun insert(user: User) = db?.getUserDao()?.insert(user)

    fun getUsers() = db?.getUserDao()?.getUsers()

    fun getUser(id: Long) = db?.getUserDao()?.getUser(id)

    suspend fun deleteUser(user: User) = db?.getUserDao()?.deleteUser(user)
}