package com.entalpiya.app.auth.domain.repository

import com.entalpiya.app.core.data.remote.HealthCheckResponse
import com.entalpiya.app.core.utils.SimpleResource

interface AuthRepository {
    suspend fun login(email: String, password: String): SimpleResource
}