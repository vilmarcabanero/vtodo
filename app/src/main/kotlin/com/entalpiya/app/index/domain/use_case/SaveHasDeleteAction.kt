package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.repository.TodoRepository
import javax.inject.Inject

class SaveHasDeleteAction @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke() {
        repository.saveHasDeleteAction()
    }
}