package com.entalpiya.app.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.entalpiya.app.feature_home.data.local.model.TaskEntity
import com.entalpiya.app.feature_home.data.local.TaskDao

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}