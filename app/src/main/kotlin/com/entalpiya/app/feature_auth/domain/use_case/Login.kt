package com.entalpiya.app.feature_auth.domain.use_case

import com.entalpiya.app.feature_auth.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.login(email, password)
    }
}