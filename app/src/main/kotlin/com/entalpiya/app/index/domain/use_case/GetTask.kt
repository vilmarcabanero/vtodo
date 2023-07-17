package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.repository.TaskRepository
import javax.inject.Inject

class GetTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: String): Task? {
        return repository.getTaskById(id)
    }
}