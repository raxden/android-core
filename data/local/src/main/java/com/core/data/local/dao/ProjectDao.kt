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

    @Query("SELECT * FROM project WHERE project_username = :username")
    suspend fun findAll(username: String): List<Project>

    @Query("SELECT * FROM project WHERE project_username = :username AND name = :projectName")
    suspend fun find(username: String, projectName: String): Project

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg project: Project)

    @Query("DELETE FROM project")
    suspend fun deleteAll()
}
