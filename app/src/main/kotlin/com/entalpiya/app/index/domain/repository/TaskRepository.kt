package com.entalpiya.app.index.domain.repository

import com.entalpiya.app.index.domain.model.Task

interface TaskRepository {
    suspend fun getAllTasks(): List<Task>
    suspend fun insertTask(task: Task)
    suspend fun deleteTask(id: String)
    suspend fun toggleCompleteTask(id: String, isComplete: Boolean)
    suspend fun getTaskById(id: String): Task?
    suspend fun getHasDeleteAction(): String?
    suspend fun saveHasDeleteAction()
    suspend fun deleteHasDeleteAction()
}