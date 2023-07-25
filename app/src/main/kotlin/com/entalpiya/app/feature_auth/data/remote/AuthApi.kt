package com.entalpiya.app.feature_auth.data.remote

import com.entalpiya.app.feature_auth.data.remote.request.LoginPayload
import com.entalpiya.app.feature_auth.data.remote.response.AuthResponse
import com.entalpiya.app.feature_auth.data.remote.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("/api/auth/login")
    suspend fun login(@Body loginPayload: LoginPayload): AuthResponse

    @GET("/api/auth/user")
    suspend fun getUser(): UserResponse
}