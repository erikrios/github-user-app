package io.erikrios.github.githubuserapp.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_AVATAR_URL
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_BIO
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_BLOG
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_COMPANY
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_CREATED_AT
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_FOLLOWERS
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_FOLLOWING
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_HIREABLE
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_HTML_URL
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_ID
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_LOCATION
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_NAME
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_PUBLIC_GISTS
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_PUBLIC_REPOSITORIES
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_TYPE
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_UPDATED_AT
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_USERNAME
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class User(
    @SerializedName("login")
    @ColumnInfo(name = COLUMN_USERNAME)
    var username: String = "",
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long = 0,
    @SerializedName("name")
    @ColumnInfo(name = COLUMN_NAME)
    var name: String? = null,
    @SerializedName("location")
    @ColumnInfo(name = COLUMN_LOCATION)
    var location: String? = null,
    @SerializedName("public_repos")
    @ColumnInfo(name = COLUMN_PUBLIC_REPOSITORIES)
    var publicRepositories: Int? = null,
    @SerializedName("public_gists")
    @ColumnInfo(name = COLUMN_PUBLIC_GISTS)
    var publicGists: Int? = null,
    @SerializedName("company")
    @ColumnInfo(name = COLUMN_COMPANY)
    var company: String? = null,
    @SerializedName("followers")
    @ColumnInfo(name = COLUMN_FOLLOWERS)
    var followers: Int? = null,
    @SerializedName("following")
    @ColumnInfo(name = COLUMN_FOLLOWING)
    var following: Int? = null,
    @SerializedName("avatar_url")
    @ColumnInfo(name = COLUMN_AVATAR_URL)
    var avatarUrl: String = "",
    @SerializedName("type")
    @ColumnInfo(name = COLUMN_TYPE)
    var type: String = "",
    @SerializedName("html_url")
    @ColumnInfo(name = COLUMN_HTML_URL)
    var htmlUrl: String = "",
    @SerializedName("blog")
    @ColumnInfo(name = COLUMN_BLOG)
    var blog: String? = null,
    @SerializedName("bio")
    @ColumnInfo(name = COLUMN_BIO)
    var bio: String? = null,
    @SerializedName("hireable")
    @ColumnInfo(name = COLUMN_HIREABLE)
    var hireable: Boolean? = null,
    @SerializedName("created_at")
    @ColumnInfo(name = COLUMN_CREATED_AT)
    var createdAt: String? = null,
    @SerializedName("updated_at")
    @ColumnInfo(name = COLUMN_UPDATED_AT)
    var updatedAt: String = ""
) : Parcelable
