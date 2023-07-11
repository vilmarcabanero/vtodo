package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.repository.TodoRepository
import javax.inject.Inject

class ToggleCompleteTodo @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: String, isComplete: Boolean) {
        repository.toggleCompleteTodo(id, isComplete)
    }
}