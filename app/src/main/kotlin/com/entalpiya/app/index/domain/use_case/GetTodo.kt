package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.domain.repository.TodoRepository
import javax.inject.Inject

class GetTodo @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: String): Todo? {
        return repository.getTodoById(id)
    }
}