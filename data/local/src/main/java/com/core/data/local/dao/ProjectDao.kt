package com.core.data.local.dao

import androidx.room.*
import com.core.domain.Project

/**
 * Data Access Object for the users table.
 */
@Dao
interface ProjectDao {

    @Query("SELECT * FROM project")
    suspend fun findAll(): List<Project>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg project: Project)

    @Query("DELETE FROM project")
    suspend fun deleteAll()
}
