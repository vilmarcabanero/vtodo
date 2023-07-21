package com.entalpiya.app.auth.domain.repository

interface UserRepository {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String?
    suspend fun saveUserCreatedAt(date: String)
    suspend fun getUserCreatedAt(): String?
}