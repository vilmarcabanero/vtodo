package com.entalpiya.app.auth.di

import android.content.SharedPreferences
import com.entalpiya.app.auth.data.repository.UserRepositoryImpl
import com.entalpiya.app.auth.domain.repository.UserRepository
import com.entalpiya.app.auth.domain.use_case.AuthUseCases
import com.entalpiya.app.auth.domain.use_case.GetUserCreatedAt
import com.entalpiya.app.auth.domain.use_case.GetUserId
import com.entalpiya.app.auth.domain.use_case.SaveUserCreatedAt
import com.entalpiya.app.auth.domain.use_case.SaveUserId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences): UserRepository {
        return UserRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(userRepository: UserRepository): AuthUseCases {
        return AuthUseCases(
            saveUserId = SaveUserId(userRepository),
            getUserId = GetUserId(userRepository),
            saveUserCreatedAt = SaveUserCreatedAt(userRepository),
            getUserCreatedAt = GetUserCreatedAt(userRepository),
        )
    }
}