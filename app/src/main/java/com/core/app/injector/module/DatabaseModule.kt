package com.core.app.injector.module

import android.content.Context
import androidx.room.Room
import com.core.data.persistence.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @JvmStatic
    @Provides
    @Singleton
    internal fun appDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "app_database.db"
        ).build()
    }
}
