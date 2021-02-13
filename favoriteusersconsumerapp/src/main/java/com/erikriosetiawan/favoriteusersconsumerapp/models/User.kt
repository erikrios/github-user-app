package com.erikriosetiawan.favoriteusersconsumerapp.models

data class User(
    var username: String = "",
    var id: Long = 0,
    var name: String? = null,
    var location: String? = null,
    var publicRepositories: Int? = null,
    var publicGists: Int? = null,
    var company: String? = null,
    var followers: Int? = null,
    var following: Int? = null,
    var avatarUrl: String = "",
    var type: String = "",
    var htmlUrl: String = "",
    var blog: String? = null,
    var bio: String? = null,
    var hireable: Boolean? = null,
    var createdAt: String? = null,
    var updatedAt: String = ""
)