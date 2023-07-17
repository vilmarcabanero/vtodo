package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.repository.TaskRepository
import javax.inject.Inject

class ToggleCompleteTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: String, isComplete: Boolean) {
        repository.toggleCompleteTask(id, isComplete)
    }
}