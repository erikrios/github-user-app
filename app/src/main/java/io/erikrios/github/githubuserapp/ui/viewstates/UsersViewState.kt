package io.erikrios.github.githubuserapp.ui.viewstates

import io.erikrios.github.githubuserapp.models.User

data class UsersViewState(
    var loading: Boolean = false,
    var responseCode: Int? = null,
    var users: List<User>? = null,
    var exception: Exception? = null
)
