package com.entalpiya.app.feature_auth.domain.use_case

import com.entalpiya.app.feature_auth.domain.model.User
import com.entalpiya.app.feature_auth.domain.repository.UserRepository
import com.entalpiya.app.core.utils.Resource
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return repository.getUser()
    }
}