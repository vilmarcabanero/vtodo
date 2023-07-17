package com.entalpiya.app.index.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.entalpiya.app.index.data.local.model.TaskEntity

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getAllTasks(): List<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todo: TaskEntity)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Query("UPDATE task SET isComplete = :isComplete WHERE id = :id")
    suspend fun toggleCompleteTask(id: String, isComplete: Boolean)

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskById(id: String): TaskEntity?
}