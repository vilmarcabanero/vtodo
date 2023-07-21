package com.entalpiya.app.index.presentation.add_edit_task

data class AddEditTaskState(
    val isPopUpOpen: Boolean = false,
    val addTaskSuccess: Boolean = false,
    val hasDeleteAction: Boolean = false,
    val userId: String = ""
)
