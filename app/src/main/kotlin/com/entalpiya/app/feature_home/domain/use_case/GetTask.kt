package com.entalpiya.app.feature_home.domain.use_case

import com.entalpiya.app.feature_home.domain.model.Task
import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class GetTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: String): Task? {
        return repository.getTaskById(id)
    }
}