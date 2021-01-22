package io.erikrios.github.githubuserapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @SerializedName("login")
    var username: String,
    @SerializedName("id")
    var id: Long,
    @SerializedName("name")
    var name: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("public_repos")
    var repository: Int?,
    @SerializedName("company")
    var company: String?,
    @SerializedName("followers")
    var followers: Int?,
    @SerializedName("following")
    var following: Int?,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("html_url")
    var htmlUrl: String,
    @SerializedName("blog")
    var blog: String?,
    @SerializedName("bio")
    var bio: String?,
    @SerializedName("hireable")
    var hireable: Boolean?,
    @SerializedName("created_at")
    var createdAt: String?,
    @SerializedName("updated_at")
    var updatedAt: String?
) : Parcelable
