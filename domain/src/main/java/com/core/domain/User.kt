package com.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class User(
    var id: Long = 0,
    var username: String,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : Parcelable