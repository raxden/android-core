package com.core.data.local.dao

import androidx.room.*
import com.core.domain.Account

/**
 * Data Access Object for the users table.
 */
@Dao
interface AccountDao {

    @Query("SELECT * FROM Account WHERE id= :id")
    suspend fun find(id: Long): Account

    @Query("SELECT * FROM Account")
    suspend fun findAll(): List<Account>

    @Query("DELETE FROM Account WHERE id= :id")
    suspend fun delete(id: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Account): Long

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("DELETE FROM Account")
    suspend fun deleteAll()
}
