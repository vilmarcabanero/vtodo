package com.entalpiya.app.index.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.entalpiya.app.index.domain.model.Task

@Entity(tableName = "task")
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean,
    val createdAt: Long,
) {
    fun toTask() = Task(id, userId, title, description, isComplete, createdAt)
}
