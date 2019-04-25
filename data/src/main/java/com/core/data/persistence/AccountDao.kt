package com.core.data.persistence

import androidx.room.*
import com.core.domain.Account
import io.reactivex.Completable
import io.reactivex.Maybe

/**
 * Data Access Object for the users table.
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM Account WHERE id= :id")
    fun find(id: String): Maybe<Account>

    @Query("SELECT * FROM Account")
    fun findAll(): Maybe<List<Account>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account): Completable

    @Update
    fun update(account: Account): Completable
}
