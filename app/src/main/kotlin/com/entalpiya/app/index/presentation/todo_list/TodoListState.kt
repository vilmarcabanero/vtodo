package com.entalpiya.app.index.presentation.todo_list

import com.entalpiya.app.index.domain.model.Todo

data class TodoListState(
    val todos: List<Todo> = emptyList(),
    val selectedTodo: Todo? = null,
)
