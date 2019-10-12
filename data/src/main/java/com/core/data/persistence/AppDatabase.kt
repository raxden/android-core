package com.core.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.core.data.persistence.converter.LocalDateTimeConverter
import com.core.data.persistence.converter.UriConverter
import com.core.data.persistence.dao.AccountDao
import com.core.data.persistence.dao.ProjectDao
import com.core.domain.Account
import com.core.domain.Project
import com.core.domain.User

@Database(
        entities = [
            Account::class,
            Project::class
        ],
        version = 2,
        exportSchema = false
)
@TypeConverters(
        UriConverter::class,
        LocalDateTimeConverter::class
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun projectDao(): ProjectDao
}