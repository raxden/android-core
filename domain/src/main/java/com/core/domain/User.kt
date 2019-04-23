package com.core.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Long = 0,
        var username: String
) : Parcelable