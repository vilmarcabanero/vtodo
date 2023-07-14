package com.entalpiya.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.entalpiya.app.index.data.local.model.TodoEntity
import com.entalpiya.app.index.data.local.TodoDao

@Database(
    entities = [TodoEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val todoDao: TodoDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}