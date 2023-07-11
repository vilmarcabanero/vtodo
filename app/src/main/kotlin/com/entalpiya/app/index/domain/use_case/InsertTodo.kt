package com.entalpiya.app.index.domain.use_case

import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.domain.repository.TodoRepository
import javax.inject.Inject

class InsertTodo @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) {
        repository.insertTodo(todo)
    }
}