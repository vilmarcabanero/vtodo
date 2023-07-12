package com.entalpiya.app.index.data.repository

import android.content.SharedPreferences
import com.entalpiya.app.core.data.local.model.TodoEntity
import com.entalpiya.app.index.data.local.TodoDao
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.domain.repository.TodoRepository
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val dao: TodoDao,
    private val prefs: SharedPreferences
) : TodoRepository {
    override suspend fun getAllTodos(): List<Todo> {
        return dao.getAllTodos().map { it.toTodo() }
    }

    override suspend fun insertTodo(todo: Todo) {
        dao.insertTodo(
            TodoEntity(todo.id, todo.title, todo.description, todo.isComplete)
        )
    }

    override suspend fun deleteTodo(id: String) {
        dao.deleteTodo(id)
    }

    override suspend fun toggleCompleteTodo(id: String, isComplete: Boolean) {
        dao.toggleCompleteTodo(id, isComplete)
    }

    override suspend fun getTodoById(id: String): Todo? {
        return dao.getTodoById(id)?.toTodo()
    }

    override suspend fun getHasDeleteAction(): String? {
        return prefs.getString("hasDeleteAction", null)
    }

    override suspend fun saveHasDeleteAction() {
        prefs.edit().putString("hasDeleteAction", "true").apply()
    }

    override suspend fun deleteHasDeleteAction() {
        prefs.edit().remove("hasDeleteAction").apply()
    }
}