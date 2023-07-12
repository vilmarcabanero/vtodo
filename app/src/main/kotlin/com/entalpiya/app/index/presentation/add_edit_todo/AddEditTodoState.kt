package com.entalpiya.app.index.presentation.add_edit_todo

import com.entalpiya.app.index.domain.model.Todo

data class AddEditTodoState(
    val isPopUpOpen: Boolean = false,
    val addTodoSuccess: Boolean = false,
    val hasDeleteAction: Boolean = false,
)
