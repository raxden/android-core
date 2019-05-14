package com.core.data.persistence

import androidx.room.EmptyResultSetException
import androidx.room.Room
import com.core.data.BaseTest
import com.core.domain.Account
import org.junit.After
import org.junit.Before
import org.junit.Test

class AccountDaoTest : BaseTest() {

    private lateinit var database: AppDatabase
    private lateinit var account: Account

    @Before
    fun initDb() {
        // using an in-memory database because the information stored here disappears after test
        database = Room.inMemoryDatabaseBuilder(getContext(), AppDatabase::class.java)
                // allowing main thread queries, just for testing
                .allowMainThreadQueries()
                .build()

        account = Account(username = "username").also {
            it.id = database.accountDao().insert(it).blockingGet()
        }
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun retrieveNotExists() {
        database.accountDao().find(999)
                .test()
                .assertError(EmptyResultSetException::class.java)
    }

    @Test
    fun retrieve() {
        database.accountDao().find(account.id)
                .test()
                .assertNoErrors()
                .assertValue { it.id == account.id && it.username == account.username }
    }

    @Test
    fun insert() {
        database.accountDao().find(account.id)
                .test()
                .assertNoErrors()
                .assertValue { it.id == account.id && it.username == account.username }
    }

    @Test
    fun update() {
        account.username = "usernameUpdated"
        database.accountDao().update(account).blockingAwait()
        database.accountDao().find(account.id)
                .test()
                .assertNoErrors()
                .assertValue { it.id == account.id && it.username == account.username }
    }

    @Test
    fun delete() {
        database.accountDao().delete(account.id).blockingAwait()
        database.accountDao().find(account.id)
                .test()
                .assertNoValues()
    }
}