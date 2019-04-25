package com.core.data.persistence

import androidx.room.*
import com.core.domain.User
import io.reactivex.Completable
import io.reactivex.Maybe

import io.reactivex.Single

/**
 * Data Access Object for the users table.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE id= :id")
    fun find(id: String): Maybe<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Completable

    @Update
    fun update(user: User): Completable
}
