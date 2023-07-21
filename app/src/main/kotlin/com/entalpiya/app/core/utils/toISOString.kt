package com.entalpiya.app.core.utils


import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun toISOString(date: Date): String = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").format(date)

