package com.entalpiya.app.core.data.util

import com.entalpiya.app.core.data.remote.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException

fun getErrorResponse(e: HttpException): ErrorResponse {
    val errorBody = e.response()?.errorBody()?.string()
    return Gson().fromJson(errorBody, ErrorResponse::class.java)
}