package com.entalpiya.app.index.presentation

import com.entalpiya.app.index.domain.model.Todo

data class IndexState(
    val todos: List<Todo> = emptyList(),
)
