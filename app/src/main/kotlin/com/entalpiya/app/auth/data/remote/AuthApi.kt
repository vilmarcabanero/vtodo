package com.entalpiya.app.auth.data.remote

import com.entalpiya.app.auth.data.remote.request.LoginPayload
import com.entalpiya.app.auth.data.remote.response.AuthResponse
import com.entalpiya.app.core.data.remote.HealthCheckResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/login")
    suspend fun login(@Body loginPayload: LoginPayload): AuthResponse
}