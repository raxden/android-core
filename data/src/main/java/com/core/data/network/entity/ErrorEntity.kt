package com.core.data.network.entity

import com.google.gson.annotations.Expose

data class ErrorEntity(
        @Expose val message: String? = null,
        @Expose val documentation_url: String? = null
)