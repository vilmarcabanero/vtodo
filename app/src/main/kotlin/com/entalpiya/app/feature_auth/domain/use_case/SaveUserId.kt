package com.entalpiya.app.feature_auth.domain.use_case

import com.entalpiya.app.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserId @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(userId: String) {
        repository.saveUserId(userId)
    }
}
