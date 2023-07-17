package com.entalpiya.app.index.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean,
    val createdAt: Long,
): Parcelable