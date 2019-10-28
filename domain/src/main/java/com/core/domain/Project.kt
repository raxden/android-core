package com.core.domain

import android.net.Uri
import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
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
) : Parcelable {

    override fun toString(): String = "[$id] $name"
}
