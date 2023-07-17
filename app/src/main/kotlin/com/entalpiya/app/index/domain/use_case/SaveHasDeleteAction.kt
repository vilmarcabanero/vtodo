package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.repository.TaskRepository
import javax.inject.Inject

class SaveHasDeleteAction @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke() {
        repository.saveHasDeleteAction()
    }
}