package com.core.data.network.entity

import com.google.gson.annotations.Expose

data class UserEntity(
        @Expose val login: String? = null,
        @Expose val id: Long? = null,
        @Expose val node_id: String? = null,
        @Expose val avatar_url: String? = null,
        @Expose val type: String? = null,
        @Expose val name: String? = null,
        @Expose val company: String? = null,
        @Expose val blog: String? = null,
        @Expose val location: String? = null,
        @Expose val email: String? = null,
        @Expose val public_repos: String? = null,
        @Expose val public_gists: String? = null,
        @Expose val followers: String? = null,
        @Expose val following: String? = null,
        @Expose val created_at: String? = null,
        @Expose val updated_at: String? = null
)
