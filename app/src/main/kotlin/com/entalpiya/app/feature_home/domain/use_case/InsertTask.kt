package com.entalpiya.app.feature_home.domain.use_case

import com.entalpiya.app.feature_home.domain.model.Task
import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class InsertTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.insertTask(task)
    }
}