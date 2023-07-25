package com.entalpiya.app.feature_auth.domain.repository

import com.entalpiya.app.core.utils.SimpleResource

interface AuthRepository {
    suspend fun login(email: String, password: String): SimpleResource
}