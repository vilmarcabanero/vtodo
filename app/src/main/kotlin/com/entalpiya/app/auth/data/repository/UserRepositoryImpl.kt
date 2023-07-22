package com.entalpiya.app.auth.data.repository

import android.content.SharedPreferences
import com.entalpiya.app.auth.domain.repository.UserRepository
import com.entalpiya.app.core.utils.Constants
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserRepository {
    override suspend fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(Constants.KEY_USER_ID, userId).apply()
    }

    override suspend fun getUserId(): String? {
        return sharedPreferences.getString(Constants.KEY_USER_ID, null)
    }

    override suspend fun saveUserCreatedAt(date: String) {
        sharedPreferences.edit().putString(Constants.KEY_USER_CREATED_AT, date).apply()
    }

    override suspend fun getUserCreatedAt(): String? {
        return sharedPreferences.getString(Constants.KEY_USER_CREATED_AT, null)
    }
}