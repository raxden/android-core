package com.core.data.model

import com.google.gson.annotations.Expose

data class ErrorEntity(
        @Expose val message: String? = null,
        @Expose val documentation_url: String? = null
)