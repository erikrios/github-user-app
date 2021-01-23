package io.erikrios.github.githubuserapp.ui.viewstates

import io.erikrios.github.githubuserapp.models.User

data class UserViewState(
    var loading: Boolean = false,
    var responseCode: Int? = null,
    var user: User? = null,
    var exception: Exception? = null
)