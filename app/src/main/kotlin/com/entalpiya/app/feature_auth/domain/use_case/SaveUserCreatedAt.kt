package com.entalpiya.app.feature_auth.domain.use_case

import com.entalpiya.app.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserCreatedAt @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(date: String) {
        repository.saveUserCreatedAt(date)
    }
}