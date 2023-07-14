package com.entalpiya.app.index.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.entalpiya.app.index.domain.model.Todo

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean,
    val createdAt: Long,
) {
    fun toTodo() = Todo(id, title, description, isComplete, createdAt)
}
