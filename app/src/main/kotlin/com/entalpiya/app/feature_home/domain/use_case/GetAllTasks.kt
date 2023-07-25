package com.entalpiya.app.feature_home.domain.use_case

import com.entalpiya.app.feature_home.domain.model.Task
import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class GetAllTasks @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(): List<Task> {
        return repository.getAllTasks().sortedBy { it.createdAt }
    }
}