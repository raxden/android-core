package com.core.domain

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

import org.threeten.bp.LocalDateTime

@Parcelize
data class Project(
        var id: Long? = null,
        var name: String? = null,
        var description: String? = null,
        var user: User? = null,
        var uri: Uri? = null,
        var createdAt: LocalDateTime? = null,
        var updatedAt: LocalDateTime? = null
) : Parcelable
