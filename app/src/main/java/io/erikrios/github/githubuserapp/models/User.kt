package io.erikrios.github.githubuserapp.models

data class User(
    var username: String,
    var name: String,
    var location: String,
    var repository: Int,
    var company: String,
    var followers: Int,
    var following: Int,
    var avatar: Int
) {
    val url get() = "https://github.com/$username"
}
