package com.entalpiya.app.index.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Todo(
    val id: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean
): Parcelable