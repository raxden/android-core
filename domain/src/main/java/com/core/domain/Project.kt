package com.core.domain

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import org.threeten.bp.LocalDateTime

@Parcelize
data class Project(
        var id: Long,
        var name: String,
        var description: String,
        var user: User,
        var uri: Uri,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime
) : Parcelable
