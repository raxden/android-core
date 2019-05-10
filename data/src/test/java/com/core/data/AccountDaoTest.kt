package com.core.data

import androidx.room.Room
import com.core.data.persistence.AppDatabase
import com.core.domain.Account
import org.junit.After
import org.junit.Before
import org.junit.Test

class AccountDaoTest : BaseTest() {

    private lateinit var database: AppDatabase

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(getContext(), AppDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertAccount() {
        Account(username = "username").run {
            id = database.accountDao().insert(this).blockingGet()
            database.accountDao().find(id)
                    .test()
                    .assertValue { it.id == id && it.username == username }
        }
    }

    @Test
    fun updateAccount() {
        Account(username = "username").run {
            id = database.accountDao().insert(this).blockingGet()
            username = "usernameUpdated"
            database.accountDao().update(this).blockingAwait()
            database.accountDao().find(id)
                    .test()
                    .assertValue { it.id == id && it.username == username }
        }
    }

    @Test
    fun deleteAccount() {
        Account(username = "username").run {
            id = database.accountDao().insert(this).blockingGet()
            database.accountDao().delete(id).blockingAwait()
            database.accountDao().find(id)
                    .test()
                    .assertNoValues()
        }
    }
}