package com.entalpiya.app.auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.entalpiya.app.R
import com.entalpiya.app.auth.data.remote.AuthApi
import com.entalpiya.app.auth.domain.model.User
import com.entalpiya.app.auth.domain.repository.UserRepository
import com.entalpiya.app.core.data.util.getErrorResponse
import com.entalpiya.app.core.utils.Constants
import com.entalpiya.app.core.utils.Resource
import com.entalpiya.app.core.utils.UiText
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val api: AuthApi
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

    override suspend fun getUser(): Resource<User> {
        return try {
            val response = api.getUser().toUser()
            Resource.Success(response)
        } catch (e: HttpException) {
            val errorResponse = getErrorResponse(e)
            println(errorResponse)

            Log.w("UserRepositoryImpl", errorResponse.message)
            Resource.NullError()
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        }
    }
}