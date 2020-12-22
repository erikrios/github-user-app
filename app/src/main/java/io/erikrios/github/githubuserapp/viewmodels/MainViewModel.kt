package io.erikrios.github.githubuserapp.viewmodels

import android.content.res.TypedArray
import io.erikrios.github.githubuserapp.models.User

class MainViewModel(
    private val usernames: Array<String>,
    private val names: Array<String>,
    private val locations: Array<String>,
    private val repositories: Array<String>,
    private val companies: Array<String>,
    private val followers: Array<String>,
    private val followings: Array<String>,
    private val avatars: TypedArray
) {
    fun getUsers(): List<User> {
        val users = mutableListOf<User>()
        for (position in usernames.indices) {
            val user = User(
                usernames[position],
                names[position],
                locations[position],
                repositories[position].toInt(),
                companies[position],
                followers[position].toInt(),
                followings[position].toInt(),
                avatars.getResourceId(position, -1)
            )
            users.add(user)
        }
        return users
    }
}