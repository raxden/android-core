package com.core.data.entity

import com.google.gson.annotations.Expose

data class UserEntity(
    @Expose val id: Long? = null,
    @Expose val name: String? = null,
    @Expose val createdAt: String? = null,
    @Expose val updatedAt: String? = null
)