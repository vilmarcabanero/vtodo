package com.entalpiya.app.core.data.remote

data class ErrorResponse(
    val statusCode: Int,
    val message: String,
    val error: String,
)
