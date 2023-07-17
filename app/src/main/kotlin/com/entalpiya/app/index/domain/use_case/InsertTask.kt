package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.repository.TaskRepository
import javax.inject.Inject

class InsertTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        repository.insertTask(task)
    }
}