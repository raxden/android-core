package com.core.data.persistence.dao

import androidx.room.*
import com.core.domain.Account
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Data Access Object for the users table.
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM Account WHERE id= :id")
    fun find(id: Long): Single<Account>

    @Query("SELECT * FROM Account")
    fun findAll(): Maybe<List<Account>>

    @Query("DELETE FROM Account WHERE id= :id")
    fun delete(id: Long): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account): Single<Long>

    @Update
    fun update(account: Account): Completable

    @Delete
    fun delete(account: Account): Completable

    @Query("DELETE FROM Account")
    fun deleteAll(): Completable
}
