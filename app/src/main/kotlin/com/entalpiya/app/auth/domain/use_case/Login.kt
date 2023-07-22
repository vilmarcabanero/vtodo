package com.entalpiya.app.auth.domain.use_case

import com.entalpiya.app.auth.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) {
        repository.login(email, password)
    }
}