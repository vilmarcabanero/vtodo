package com.entalpiya.app.index.di

import android.content.SharedPreferences
import com.entalpiya.app.core.data.local.AppDatabase
import com.entalpiya.app.index.data.local.TaskDao
import com.entalpiya.app.index.data.repository.TaskRepositoryImpl
import com.entalpiya.app.index.domain.repository.TaskRepository
import com.entalpiya.app.index.domain.use_case.DeleteHasDeleteAction
import com.entalpiya.app.index.domain.use_case.DeleteTask
import com.entalpiya.app.index.domain.use_case.GetAllTasks
import com.entalpiya.app.index.domain.use_case.GetHasDeleteAction
import com.entalpiya.app.index.domain.use_case.GetTask
import com.entalpiya.app.index.domain.use_case.InsertTask
import com.entalpiya.app.index.domain.use_case.SaveHasDeleteAction
import com.entalpiya.app.index.domain.use_case.TaskUseCases
import com.entalpiya.app.index.domain.use_case.ToggleCompleteTask
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskModule {

    @Provides
    @Singleton
    fun provideTaskDao(database: AppDatabase): TaskDao {
        return database.taskDao
    }

    @Provides
    @Singleton
    fun provideTaskRepository(taskDao: TaskDao, prefs: SharedPreferences): TaskRepository {
        return TaskRepositoryImpl(taskDao, prefs)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases {
        return TaskUseCases(
            GetAllTasks(repository),
            InsertTask(repository),
            DeleteTask(repository),
            ToggleCompleteTask(repository),
            GetTask(repository),
            GetHasDeleteAction(repository),
            SaveHasDeleteAction(repository),
            DeleteHasDeleteAction(repository)
        )
    }
}