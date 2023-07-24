package com.entalpiya.app.auth.domain.repository

import com.entalpiya.app.auth.domain.model.User
import com.entalpiya.app.core.utils.Resource

interface UserRepository {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String?
    suspend fun saveUserCreatedAt(date: String)
    suspend fun getUserCreatedAt(): String?
    suspend fun getUser(): Resource<User>
}