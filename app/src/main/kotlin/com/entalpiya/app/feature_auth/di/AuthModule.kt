package com.entalpiya.app.feature_auth.di

import android.content.SharedPreferences
import com.entalpiya.app.BuildConfig
import com.entalpiya.app.feature_auth.data.remote.AuthApi
import com.entalpiya.app.feature_auth.data.repository.AuthRepositoryImpl
import com.entalpiya.app.feature_auth.data.repository.UserRepositoryImpl
import com.entalpiya.app.feature_auth.domain.repository.AuthRepository
import com.entalpiya.app.feature_auth.domain.repository.UserRepository
import com.entalpiya.app.feature_auth.domain.use_case.AuthUseCases
import com.entalpiya.app.feature_auth.domain.use_case.GetUser
import com.entalpiya.app.feature_auth.domain.use_case.GetUserCreatedAt
import com.entalpiya.app.feature_auth.domain.use_case.GetUserId
import com.entalpiya.app.feature_auth.domain.use_case.Login
import com.entalpiya.app.feature_auth.domain.use_case.SaveUserCreatedAt
import com.entalpiya.app.feature_auth.domain.use_case.SaveUserId
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(client: OkHttpClient): AuthApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences, authApi: AuthApi): UserRepository {
        return UserRepositoryImpl(sharedPreferences, authApi)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(authApi: AuthApi, sharedPreferences: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(authApi, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(userRepository: UserRepository, authRepository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            saveUserId = SaveUserId(userRepository),
            getUserId = GetUserId(userRepository),
            saveUserCreatedAt = SaveUserCreatedAt(userRepository),
            getUserCreatedAt = GetUserCreatedAt(userRepository),
            login = Login(authRepository),
            getUser = GetUser(userRepository),
        )
    }
}