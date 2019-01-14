package com.core.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
data class User(
    var id: Long,
    var name: String,
    var createdAt: LocalDateTime,
    var updatedAt: LocalDateTime
) : Parcelable