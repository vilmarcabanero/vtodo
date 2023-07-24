package com.entalpiya.app.auth.domain.use_case

import com.entalpiya.app.auth.domain.model.User
import com.entalpiya.app.auth.domain.repository.UserRepository
import com.entalpiya.app.core.utils.Resource
import javax.inject.Inject

class GetUser @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return repository.getUser()
    }
}