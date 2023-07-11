package com.entalpiya.app.index.domain.model

data class Todo(
    val id: String,
    val title: String,
    val description: String?,
    val isComplete: Boolean
)
