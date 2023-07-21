package com.entalpiya.app.auth.data.repository

import android.content.SharedPreferences
import com.entalpiya.app.auth.domain.repository.UserRepository
import com.entalpiya.app.core.utils.SharedPreferencesKeys
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserRepository {
    override suspend fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(SharedPreferencesKeys.USER_ID, userId).apply()
    }

    override suspend fun getUserId(): String? {
        return sharedPreferences.getString(SharedPreferencesKeys.USER_ID, null)
    }

    override suspend fun saveUserCreatedAt(date: String) {
        sharedPreferences.edit().putString(SharedPreferencesKeys.USER_CREATED_AT, date).apply()
    }

    override suspend fun getUserCreatedAt(): String? {
        return sharedPreferences.getString(SharedPreferencesKeys.USER_CREATED_AT, null)
    }
}