package com.core.data.local.dao

import androidx.room.*
import com.core.domain.Account

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
