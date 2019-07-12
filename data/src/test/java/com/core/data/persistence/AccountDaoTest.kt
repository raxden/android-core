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
    fun retrieveNotExists() {
        database.accountDao().find(999)
                .test()
                .assertError(EmptyResultSetException::class.java)
    }

    @Test
    fun retrieveById() {
        database.accountDao().apply {
            val account = Account(username = "username").also {
                it.id = insert(it).blockingGet()
            }
            find(account.id)
                    .test()
                    .assertNoErrors()
                    .assertValue { it.id == account.id && it.username == account.username }
        }
    }

    @Test
    fun insert() {
        database.accountDao().apply {
            insert(Account(username = "username"))
                    .test()
                    .assertNoErrors()
                    .assertValue { it > 0 }
        }
    }

    @Test
    fun update() {
        database.accountDao().apply {
            val account = Account(username = "username").also {
                it.id = insert(it).blockingGet()
            }
            account.username = "usernameUpdated"
            update(account).blockingAwait()
            find(account.id)
                    .test()
                    .assertNoErrors()
                    .assertValue { it.id == account.id && it.username == account.username }
        }
    }

    @Test
    fun deleteById() {
        database.accountDao().apply {
            val account = Account(username = "username").also {
                it.id = insert(it).blockingGet()
            }
            delete(account.id).blockingAwait()
            find(account.id)
                    .test()
                    .assertNoValues()
        }
    }

    @Test
    fun delete() {
        database.accountDao().apply {
            val account = Account(username = "username").also {
                it.id = insert(it).blockingGet()
            }
            delete(account).blockingAwait()
            find(account.id)
                    .test()
                    .assertNoValues()
        }
    }
}