package com.erikriosetiawan.favoriteusersconsumerapp.databases

import android.content.ContentValues
import android.net.Uri
import android.provider.BaseColumns
import com.erikriosetiawan.favoriteusersconsumerapp.models.User

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

            fun toContentValues(user: User): ContentValues {
                val values = ContentValues()
                user.apply {
                    values.put(COLUMN_ID, id)
                    values.put(COLUMN_USERNAME, username)
                    name?.let { values.put(COLUMN_NAME, it) }
                    location?.let { values.put(COLUMN_LOCATION, it) }
                    publicRepositories?.let { values.put(COLUMN_PUBLIC_REPOSITORIES, it) }
                    publicGists?.let { values.put(COLUMN_PUBLIC_GISTS, it) }
                    company?.let { values.put(COLUMN_COMPANY, it) }
                    followers?.let { values.put(COLUMN_FOLLOWERS, it) }
                    following?.let { values.put(COLUMN_FOLLOWING, it) }
                    values.put(COLUMN_AVATAR_URL, avatarUrl)
                    values.put(COLUMN_TYPE, type)
                    values.put(COLUMN_HTML_URL, htmlUrl)
                    blog?.let { values.put(COLUMN_BLOG, it) }
                    bio?.let { values.put(COLUMN_BIO, it) }
                    hireable?.let { values.put(COLUMN_HIREABLE, it) }
                    createdAt?.let { values.put(COLUMN_CREATED_AT, it) }
                    values.put(COLUMN_UPDATED_AT, updatedAt)
                }
                return values
            }
        }
    }
}