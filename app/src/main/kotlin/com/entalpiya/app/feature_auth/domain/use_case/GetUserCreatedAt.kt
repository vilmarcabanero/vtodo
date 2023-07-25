package com.entalpiya.app.feature_auth.domain.use_case

import com.entalpiya.app.feature_auth.domain.repository.UserRepository
import javax.inject.Inject

class GetUserCreatedAt @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getUserCreatedAt()
    }
}