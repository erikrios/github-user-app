package io.erikrios.github.githubuserapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var username: String,
    var name: String,
    var location: String,
    var repository: Int,
    var company: String,
    var followers: Int,
    var following: Int,
    var avatar: Int
) : Parcelable {
    val url get() = "https://github.com/$username"

    override fun toString(): String =
        """
            username: ${this.username}
            name: ${this.name}
            location: ${this.location}
            repository: ${this.repository}
            company: ${this.company}
            followers: ${this.followers}
            following: ${this.following}
            avatar: ${this.avatar}
            url: ${this.url}
        """.trimIndent()
}
