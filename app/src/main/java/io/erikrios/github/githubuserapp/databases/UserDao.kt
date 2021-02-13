package io.erikrios.github.githubuserapp.databases

import androidx.room.*
import io.erikrios.github.githubuserapp.models.User
import io.erikrios.github.githubuserapp.models.User.Companion.COLUMN_ID
import io.erikrios.github.githubuserapp.models.User.Companion.COLUMN_USERNAME
import io.erikrios.github.githubuserapp.models.User.Companion.TABLE_NAME

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_USERNAME")
    fun getUsers(): List<User>

    @Query("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = :id")
    fun getUser(id: Long): User

    @Delete
    suspend fun deleteUser(user: User)
}