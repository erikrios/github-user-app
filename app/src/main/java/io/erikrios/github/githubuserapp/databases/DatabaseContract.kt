package io.erikrios.github.githubuserapp.databases

import android.content.ContentValues
import android.net.Uri
import android.provider.BaseColumns
import io.erikrios.github.githubuserapp.models.User

object DatabaseContract {

    const val AUTHORITY = "io.erikrios.github.githubuserapp"
    const val SCHEME = "content"

    class UserColumns : BaseColumns {
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

            // To create URI content://io.erikrios.github.githubuserapp/users
            val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build()

            fun fromContentValues(values: ContentValues?): User {
                val user = User()
                values?.let { contentValues ->
                    contentValues.apply {
                        if (containsKey(COLUMN_ID)) user.id = getAsLong(COLUMN_ID)
                        if (containsKey(COLUMN_USERNAME)) user.username =
                            getAsString(COLUMN_USERNAME)
                        if (containsKey(COLUMN_NAME)) user.name = getAsString(COLUMN_NAME)
                        if (containsKey(COLUMN_LOCATION)) user.location =
                            getAsString(COLUMN_LOCATION)
                        if (containsKey(COLUMN_PUBLIC_REPOSITORIES)) user.publicRepositories =
                            getAsInteger(
                                COLUMN_PUBLIC_REPOSITORIES
                            )
                        if (containsKey(COLUMN_PUBLIC_GISTS)) user.publicGists = getAsInteger(
                            COLUMN_PUBLIC_GISTS
                        )
                        if (containsKey(COLUMN_COMPANY)) user.company =
                            getAsString(COLUMN_COMPANY)
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
                        if (containsKey(COLUMN_HTML_URL)) user.htmlUrl =
                            getAsString(COLUMN_HTML_URL)
                        if (containsKey(COLUMN_BLOG)) user.blog = getAsString(COLUMN_BLOG)
                        if (containsKey(COLUMN_BIO)) user.bio = getAsString(COLUMN_BIO)
                        if (containsKey(COLUMN_HIREABLE)) user.hireable =
                            getAsBoolean(COLUMN_HIREABLE)
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
}