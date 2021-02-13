package io.erikrios.github.githubuserapp.models

import android.os.Parcelable
import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import io.erikrios.github.githubuserapp.models.User.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class User(
    @SerializedName("login")
    @ColumnInfo(name = COLUMN_USERNAME)
    var username: String,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = COLUMN_ID)
    var id: Long,
    @SerializedName("name")
    @ColumnInfo(name = COLUMN_NAME)
    var name: String?,
    @SerializedName("location")
    @ColumnInfo(name = COLUMN_LOCATION)
    var location: String?,
    @SerializedName("public_repos")
    @ColumnInfo(name = COLUMN_PUBLIC_REPOSITORIES)
    var publicRepositories: Int?,
    @SerializedName("public_gists")
    @ColumnInfo(name = COLUMN_PUBLIC_GISTS)
    var publicGists: Int?,
    @SerializedName("company")
    @ColumnInfo(name = COLUMN_COMPANY)
    var company: String?,
    @SerializedName("followers")
    @ColumnInfo(name = COLUMN_FOLLOWERS)
    var followers: Int?,
    @SerializedName("following")
    @ColumnInfo(name = COLUMN_FOLLOWING)
    var following: Int?,
    @SerializedName("avatar_url")
    @ColumnInfo(name = COLUMN_AVATAR_URL)
    var avatarUrl: String,
    @SerializedName("type")
    @ColumnInfo(name = COLUMN_TYPE)
    var type: String,
    @SerializedName("html_url")
    @ColumnInfo(name = COLUMN_HTML_URL)
    var htmlUrl: String,
    @SerializedName("blog")
    @ColumnInfo(name = COLUMN_BLOG)
    var blog: String?,
    @SerializedName("bio")
    @ColumnInfo(name = COLUMN_BIO)
    var bio: String?,
    @SerializedName("hireable")
    @ColumnInfo(name = COLUMN_HIREABLE)
    var hireable: Boolean?,
    @SerializedName("created_at")
    @ColumnInfo(name = COLUMN_CREATED_AT)
    var createdAt: String?,
    @SerializedName("updated_at")
    @ColumnInfo(name = COLUMN_UPDATED_AT)
    var updatedAt: String?
) : Parcelable {
    companion object {
        const val TABLE_NAME = "users"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_USERNAME = "username"
        const val COLUMN_NAME = "name"
        const val COLUMN_LOCATION = "location"
        const val COLUMN_PUBLIC_REPOSITORIES = "public_repositories"
        const val COLUMN_PUBLIC_GISTS = "public_gists"
        const val COLUMN_COMPANY = "company"
        const val COLUMN_FOLLOWERS = "followers"
        const val COLUMN_FOLLOWING = "following"
        const val COLUMN_AVATAR_URL = "avatar_url"
        const val COLUMN_TYPE = "type"
        const val COLUMN_HTML_URL = "html_url"
        const val COLUMN_BLOG = "blog"
        const val COLUMN_BIO = "bio"
        const val COLUMN_HIREABLE = "hireable"
        const val COLUMN_CREATED_AT = "created_at"
        const val COLUMN_UPDATED_AT = "updated_at"
    }
}
