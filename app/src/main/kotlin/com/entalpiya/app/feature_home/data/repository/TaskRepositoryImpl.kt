package com.entalpiya.app.feature_home.data.repository

import android.content.SharedPreferences
import com.entalpiya.app.feature_home.data.local.model.TaskEntity
import com.entalpiya.app.feature_home.data.local.TaskDao
import com.entalpiya.app.feature_home.domain.model.Task
import com.entalpiya.app.feature_home.domain.repository.TaskRepository
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao,
    private val prefs: SharedPreferences
) : TaskRepository {
    override suspend fun getAllTasks(): List<Task> {
        return dao.getAllTasks().map { it.toTask() }
    }

    override suspend fun insertTask(task: Task) {
        dao.insertTask(
            TaskEntity(task.id, task.userId, task.title, task.description, task.isComplete, task.createdAt)
        )
    }

    override suspend fun deleteTask(id: String) {
        dao.deleteTask(id)
    }

    override suspend fun toggleCompleteTask(id: String, isComplete: Boolean) {
        dao.toggleCompleteTask(id, isComplete)
    }

    override suspend fun getTaskById(id: String): Task? {
        return dao.getTaskById(id)?.toTask()
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