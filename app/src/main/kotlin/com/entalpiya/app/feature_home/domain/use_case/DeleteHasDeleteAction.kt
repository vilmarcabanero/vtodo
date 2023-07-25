package com.entalpiya.app.feature_home.domain.use_case

import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class DeleteHasDeleteAction @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke() {
        repository.deleteHasDeleteAction()
    }
}