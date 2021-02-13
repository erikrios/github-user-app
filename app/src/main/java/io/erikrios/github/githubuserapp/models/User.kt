package io.erikrios.github.githubuserapp.models

import android.content.ContentValues
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

        fun fromContentValues(values: ContentValues?): User {
            val user = User()
            values?.let { contentValues ->
                contentValues.apply {
                    if (containsKey(COLUMN_ID)) user.id = getAsLong(COLUMN_ID)
                    if (containsKey(COLUMN_USERNAME)) user.username = getAsString(COLUMN_USERNAME)
                    if (containsKey(COLUMN_NAME)) user.name = getAsString(COLUMN_NAME)
                    if (containsKey(COLUMN_LOCATION)) user.location = getAsString(COLUMN_LOCATION)
                    if (containsKey(COLUMN_PUBLIC_REPOSITORIES)) user.publicRepositories =
                        getAsInteger(
                            COLUMN_PUBLIC_REPOSITORIES
                        )
                    if (containsKey(COLUMN_PUBLIC_GISTS)) user.publicGists = getAsInteger(
                        COLUMN_PUBLIC_GISTS
                    )
                    if (containsKey(COLUMN_COMPANY)) user.company = getAsString(COLUMN_COMPANY)
                    if (containsKey(COLUMN_FOLLOWERS)) user.followers = getAsInteger(
                        COLUMN_FOLLOWERS
                    )
                    if (containsKey(COLUMN_FOLLOWING)) user.following = getAsInteger(
                        COLUMN_FOLLOWING
                    )
                    if (containsKey(COLUMN_AVATAR_URL)) user.avatarUrl = getAsString(
                        COLUMN_AVATAR_URL
                    )
                    if (containsKey(COLUMN_TYPE)) user.type = getAsString(COLUMN_TYPE)
                    if (containsKey(COLUMN_HTML_URL)) user.htmlUrl = getAsString(COLUMN_HTML_URL)
                    if (containsKey(COLUMN_BLOG)) user.blog = getAsString(COLUMN_BLOG)
                    if (containsKey(COLUMN_BIO)) user.bio = getAsString(COLUMN_BIO)
                    if (containsKey(COLUMN_HIREABLE)) user.hireable = getAsBoolean(COLUMN_HIREABLE)
                    if (containsKey(COLUMN_CREATED_AT)) user.createdAt = getAsString(
                        COLUMN_CREATED_AT
                    )
                    if (containsKey(COLUMN_UPDATED_AT)) user.updatedAt = getAsString(
                        COLUMN_UPDATED_AT
                    )
                }
            }
            return user
        }
    }
}
