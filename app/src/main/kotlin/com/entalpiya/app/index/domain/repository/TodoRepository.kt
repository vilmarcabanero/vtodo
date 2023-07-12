package com.entalpiya.app.index.domain.repository

import com.entalpiya.app.index.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getAllTodos(): List<Todo>
    suspend fun insertTodo(todo: Todo)
    suspend fun deleteTodo(id: String)
    suspend fun toggleCompleteTodo(id: String, isComplete: Boolean)
    suspend fun getTodoById(id: String): Todo?
    suspend fun getHasDeleteAction(): String?
    suspend fun saveHasDeleteAction()
    suspend fun deleteHasDeleteAction()
}