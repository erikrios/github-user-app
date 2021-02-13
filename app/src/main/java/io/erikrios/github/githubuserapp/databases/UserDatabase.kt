package io.erikrios.github.githubuserapp.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.erikrios.github.githubuserapp.models.User

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null
        private var LOCK = Any()
        private const val DATABASE_NAME = "favorites_user_db.db"

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}