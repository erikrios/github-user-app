package io.erikrios.github.githubuserapp.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("total_count")
    var totalCount: Int,
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean,
    @SerializedName("items")
    var users: List<User>
)
