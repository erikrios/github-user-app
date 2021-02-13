package com.erikriosetiawan.favoriteusersconsumerapp.helpers

import android.database.Cursor
import androidx.core.database.getIntOrNull
import androidx.core.database.getStringOrNull
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_AVATAR_URL
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_BIO
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_BLOG
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_COMPANY
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_CREATED_AT
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_FOLLOWERS
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_FOLLOWING
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_HIREABLE
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_HTML_URL
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_ID
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_LOCATION
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_NAME
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_PUBLIC_GISTS
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_PUBLIC_REPOSITORIES
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_TYPE
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_UPDATED_AT
import com.erikriosetiawan.favoriteusersconsumerapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_USERNAME
import com.erikriosetiawan.favoriteusersconsumerapp.models.User

object MappingHelper {

    fun mapCursorToArrayList(usersCursor: Cursor?): List<User> {
        val users = mutableListOf<User>()

        usersCursor?.apply {
            while (moveToNext()) {
                val username = getString(getColumnIndexOrThrow(COLUMN_USERNAME))
                val id = getLong(getColumnIndexOrThrow(COLUMN_ID))
                val name = getStringOrNull(getColumnIndexOrThrow(COLUMN_NAME))
                val location = getStringOrNull(getColumnIndexOrThrow(COLUMN_LOCATION))
                val publicRepositories =
                    getIntOrNull(getColumnIndexOrThrow(COLUMN_PUBLIC_REPOSITORIES))
                val publicGists = getIntOrNull(getColumnIndexOrThrow(COLUMN_PUBLIC_GISTS))
                val company = getStringOrNull(getColumnIndexOrThrow(COLUMN_COMPANY))
                val followers = getIntOrNull(getColumnIndexOrThrow(COLUMN_FOLLOWERS))
                val following = getIntOrNull(getColumnIndexOrThrow(COLUMN_FOLLOWING))
                val avatarUrl = getString(getColumnIndexOrThrow(COLUMN_AVATAR_URL))
                val type = getString(getColumnIndexOrThrow(COLUMN_TYPE))
                val htmlUrl = getString(getColumnIndexOrThrow(COLUMN_HTML_URL))
                val blog = getStringOrNull(getColumnIndexOrThrow(COLUMN_BLOG))
                val bio = getStringOrNull(getColumnIndexOrThrow(COLUMN_BIO))
                val hireable = getInt(getColumnIndexOrThrow(COLUMN_HIREABLE)) > 0
                val createdAt = getStringOrNull(getColumnIndexOrThrow(COLUMN_CREATED_AT))
                val updatedAt = getString(getColumnIndexOrThrow(COLUMN_UPDATED_AT))
                val user = User(
                    username,
                    id,
                    name,
                    location,
                    publicRepositories,
                    publicGists,
                    company,
                    followers,
                    following,
                    avatarUrl,
                    type,
                    htmlUrl,
                    blog,
                    bio,
                    hireable,
                    createdAt,
                    updatedAt
                )
                users.add(user)
            }
        }
        return users
    }
}