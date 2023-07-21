package com.entalpiya.app.auth.domain.use_case

import com.entalpiya.app.auth.domain.repository.UserRepository
import javax.inject.Inject

class GetUserId @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): String? {
        return repository.getUserId()
    }
}