package com.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.LocalDateTime

@Parcelize
data class User(
    var id: Long = 0,
    var username: String = "",
    var type: String = "",
    var name: String = "",
    var company: String = "",
    var blog: String = "",
    var location: String = "",
    var email: String = "",
    var followers: String = "",
    var following: String = "",
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : Parcelable