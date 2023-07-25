package com.entalpiya.app.feature_home.presentation.task_list

import com.entalpiya.app.feature_home.domain.model.Task

data class TaskListState(
    val tasks: List<Task> = emptyList(),
    val hasDeleteAction: Boolean = false,
)
