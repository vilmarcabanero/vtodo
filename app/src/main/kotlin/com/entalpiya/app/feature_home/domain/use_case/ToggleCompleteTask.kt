package com.entalpiya.app.feature_home.domain.use_case

import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class ToggleCompleteTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: String, isComplete: Boolean) {
        repository.toggleCompleteTask(id, isComplete)
    }
}