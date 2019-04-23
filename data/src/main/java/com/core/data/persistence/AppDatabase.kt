package com.core.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.domain.User

@Database(
        entities = arrayOf(User::class),
        version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}