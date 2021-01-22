package io.erikrios.github.githubuserapp.ui.viewstates

import io.erikrios.github.githubuserapp.models.UserResponse

data class UserResponseViewState(
    var loading: Boolean = false,
    var responseCode: Int? = null,
    var userResponse: UserResponse? = null,
    var exception: Exception? = null
)