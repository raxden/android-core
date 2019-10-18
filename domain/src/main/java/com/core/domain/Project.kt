package com.core.domain

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.threeten.bp.LocalDateTime

@Parcelize
@Entity(tableName = "project")
data class Project(
    @PrimaryKey
    var id: Long? = null,
    var name: String? = null,
    var description: String? = null,
    @Embedded(prefix = "project_")
    var user: User? = null,
    var uri: Uri? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) : Parcelable {

    override fun toString(): String = "[$id] $name"
}
