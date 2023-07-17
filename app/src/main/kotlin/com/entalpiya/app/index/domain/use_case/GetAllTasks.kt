package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.repository.TaskRepository
import javax.inject.Inject

class GetAllTasks @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): List<Task> {
        return repository.getAllTasks().sortedBy { it.createdAt }
    }
}