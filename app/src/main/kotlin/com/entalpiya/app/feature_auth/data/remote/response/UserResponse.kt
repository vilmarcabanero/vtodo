package com.entalpiya.app.feature_auth.data.remote.response

import com.entalpiya.app.feature_auth.domain.model.User

data class UserResponse(
    val id: String,
    val firstName: String,
    val lastName: String?,
    val email: String,
    val userCreatedAt: String?,
) {
    fun toUser(): User {
        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            userCreatedAt = userCreatedAt,
        )
    }
}

