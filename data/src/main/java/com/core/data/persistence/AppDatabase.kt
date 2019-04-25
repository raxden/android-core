package com.core.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.domain.Account

@Database(
        entities = [Account::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
}