package com.core.data.persistence.dao

import androidx.room.*
import com.core.domain.Project
import io.reactivex.Flowable

/**
 * Data Access Object for the users table.
 */
@Dao
interface ProjectDao {

    @Query("SELECT * FROM project")
    fun findAll(): Flowable<List<Project>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg project: Project)

    @Query("DELETE FROM project")
    fun deleteAll()
}
