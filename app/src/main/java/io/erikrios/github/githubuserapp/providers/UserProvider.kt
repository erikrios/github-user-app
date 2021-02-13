package io.erikrios.github.githubuserapp.providers

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import io.erikrios.github.githubuserapp.databases.DatabaseContract.AUTHORITY
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.TABLE_NAME
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.fromContentValues
import io.erikrios.github.githubuserapp.databases.UserDatabase
import kotlinx.coroutines.runBlocking

class UserProvider : ContentProvider() {

    companion object {
        private const val USER = 1
        private const val USER_ID = 2
        private val sUriMather = UriMatcher(UriMatcher.NO_MATCH)

        init {
            // content://io.erikrios.github.githubuserapp/users
            sUriMather.addURI(AUTHORITY, TABLE_NAME, USER)

            // content://io.erikrios.github.githubuserapp/users/id
            sUriMather.addURI(AUTHORITY, "$TABLE_NAME/#", USER_ID)
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        return when (sUriMather.match(uri)) {
            USER -> UserDatabase(requireContext()).getUserDao().getFavoriteUsers()
            USER_ID -> UserDatabase(requireContext()).getUserDao()
                .getFavoriteUser(uri.lastPathSegment.toString().toLong())
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String {
        return when (sUriMather.match(uri)) {
            USER ->
                "vnd.android.cursor.dir/$AUTHORITY.$TABLE_NAME"
            USER_ID ->
                "vnd.android.cursor.item/$AUTHORITY.$TABLE_NAME"
            else ->
                throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return when (sUriMather.match(uri)) {
            USER -> {
                runBlocking {
                    val id = UserDatabase(requireContext()).getUserDao()
                        .insert(fromContentValues(values))
                    context?.contentResolver?.notifyChange(uri, null)
                    ContentUris.withAppendedId(uri, id)
                }
            }
            USER_ID -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return when (sUriMather.match(uri)) {
            USER -> throw IllegalArgumentException("Invalid URI, cannot update without ID: $uri")
            USER_ID -> {
                val id = uri.lastPathSegment.toString().toLong()
                val user = fromContentValues(values)
                user.id = id
                runBlocking {
                    val count = UserDatabase(requireContext()).getUserDao().updateUser(user)
                    context?.contentResolver?.notifyChange(uri, null)
                    count
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return when (sUriMather.match(uri)) {
            USER -> throw IllegalArgumentException("Invalid URI, cannot delete without ID: $uri")
            USER_ID -> {
                val id = uri.lastPathSegment.toString().toLong()
                runBlocking {
                    val count = UserDatabase(requireContext()).getUserDao().deleteUserById(id)
                    context?.contentResolver?.notifyChange(uri, null)
                    count
                }
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}