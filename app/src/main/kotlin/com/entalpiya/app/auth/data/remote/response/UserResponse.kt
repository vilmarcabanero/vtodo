package com.entalpiya.app.auth.data.remote.response

import com.entalpiya.app.auth.domain.model.User
import com.google.gson.annotations.SerializedName

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

