package io.erikrios.github.githubuserapp.databases

import android.database.Cursor
import androidx.room.*
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_ID
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.COLUMN_USERNAME
import io.erikrios.github.githubuserapp.databases.DatabaseContract.UserColumns.Companion.TABLE_NAME
import io.erikrios.github.githubuserapp.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_USERNAME")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getUser(id: Long): User

    @Update
    suspend fun updateUser(user: User): Int

    @Delete
    suspend fun deleteUser(user: User): Long

    @Query("DELETE FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun deleteUserById(id: Long): Int

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_USERNAME")
    suspend fun getFavoriteUsers(): Cursor

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    suspend fun getFavoriteUser(id: Long): Cursor
}