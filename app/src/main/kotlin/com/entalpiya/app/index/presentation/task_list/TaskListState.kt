package com.entalpiya.app.index.presentation.task_list

import com.entalpiya.app.index.domain.model.Task

data class TaskListState(
    val tasks: List<Task> = emptyList(),
    val hasDeleteAction: Boolean = false,
)
