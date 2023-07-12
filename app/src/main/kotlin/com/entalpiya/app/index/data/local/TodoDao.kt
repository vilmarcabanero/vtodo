package com.entalpiya.app.index.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.entalpiya.app.core.data.local.model.TodoEntity

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    suspend fun getAllTodos(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun deleteTodo(id: String)

    @Query("UPDATE todo SET isComplete = :isComplete WHERE id = :id")
    suspend fun toggleCompleteTodo(id: String, isComplete: Boolean)

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getTodoById(id: String): TodoEntity?
}