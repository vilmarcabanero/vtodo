package com.entalpiya.app.auth.domain.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String?,
    val email: String,
    val userCreatedAt: String?,
)
