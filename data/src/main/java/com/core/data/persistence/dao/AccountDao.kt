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
    fun find(id: Long): Account

    @Query("SELECT * FROM Account")
    fun findAll(): List<Account>

    @Query("DELETE FROM Account WHERE id= :id")
    fun delete(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account): Long

    @Update
    fun update(account: Account)

    @Delete
    fun delete(account: Account)

    @Query("DELETE FROM Account")
    fun deleteAll()
}
