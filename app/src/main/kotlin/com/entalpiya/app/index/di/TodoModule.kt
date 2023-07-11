package com.entalpiya.app.index.di

import com.entalpiya.app.core.data.local.AppDatabase
import com.entalpiya.app.index.data.local.TodoDao
import com.entalpiya.app.index.data.repository.TodoRepositoryImpl
import com.entalpiya.app.index.domain.repository.TodoRepository
import com.entalpiya.app.index.domain.use_case.DeleteTodo
import com.entalpiya.app.index.domain.use_case.GetAllTodos
import com.entalpiya.app.index.domain.use_case.InsertTodo
import com.entalpiya.app.index.domain.use_case.TodoUseCases
import com.entalpiya.app.index.domain.use_case.ToggleCompleteTodo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoModule {

    @Provides
    @Singleton
    fun provideTodoDao(database: AppDatabase): TodoDao {
        return database.todoDao
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }

    @Provides
    @Singleton
    fun provideTodoUseCases(repository: TodoRepository): TodoUseCases {
        return TodoUseCases(
            GetAllTodos(repository),
            InsertTodo(repository),
            DeleteTodo(repository),
            ToggleCompleteTodo(repository)
        )
    }
}