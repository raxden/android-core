package com.core.data.entity

import android.net.Uri
import com.google.gson.annotations.Expose

data class ProjectEntity(
    @Expose val id: Long? = null,
    @Expose val name: String? = null,
    @Expose val description: String? = null,
    @Expose val user: UserEntity? = null,
    @Expose val uri: Uri? = null,
    @Expose val createdAt: String? = null,
    @Expose val updatedAt: String? = null
)
