package com.entalpiya.app.feature_auth.domain.use_case

data class AuthUseCases(
    val saveUserId: SaveUserId,
    val getUserId: GetUserId,
    val saveUserCreatedAt: SaveUserCreatedAt,
    val getUserCreatedAt: GetUserCreatedAt,
    val login: Login,
    val getUser: GetUser,
)
