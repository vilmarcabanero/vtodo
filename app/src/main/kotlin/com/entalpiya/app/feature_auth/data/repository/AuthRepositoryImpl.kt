package com.entalpiya.app.feature_auth.data.repository

import android.content.SharedPreferences
import android.util.Log
import com.entalpiya.app.R
import com.entalpiya.app.feature_auth.data.remote.AuthApi
import com.entalpiya.app.feature_auth.data.remote.request.LoginPayload
import com.entalpiya.app.feature_auth.domain.repository.AuthRepository
import com.entalpiya.app.core.data.util.getErrorResponse
import com.entalpiya.app.core.utils.Constants
import com.entalpiya.app.core.utils.Resource
import com.entalpiya.app.core.utils.SimpleResource
import com.entalpiya.app.core.utils.UiText
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences,
): AuthRepository {
    override suspend fun login(email: String, password: String): SimpleResource {
        return try {
            Log.d("AuthRepositoryImpl", "Start login request")
            val response = api.login(LoginPayload(email, password))
            sharedPreferences.edit()
                .putString(Constants.KEY_AUTH_TOKEN, response.accessToken)
                .apply()
            Log.d("AuthRepositoryImpl", "Success login request")
            Resource.Success(Unit)
        } catch (e: HttpException) {
            val errorResponse = getErrorResponse(e)
            println(errorResponse)

            Log.w("AuthRepositoryImpl", errorResponse.message)
            Resource.Error(uiText = UiText.DynamicString(errorResponse.message))
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch (e: Exception) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_unknown)
            )
        }
    }
}