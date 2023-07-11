package com.entalpiya.app.core.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.entalpiya.app.index.domain.model.Todo

@Entity(tableName = "todo")
data class TodoEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean
) {
    fun toTodo() = Todo(id, title, description, isComplete)
}