package com.entalpiya.app.index.domain.use_case

data class TaskUseCases(
    val getAllTasks: GetAllTasks,
    val insertTask: InsertTask,
    val deleteTask: DeleteTask,
    val toggleCompleteTask: ToggleCompleteTask,
    val getTask: GetTask,
    val getHasDeleteAction: GetHasDeleteAction,
    val saveHasDeleteAction: SaveHasDeleteAction,
    val deleteHasDeleteAction: DeleteHasDeleteAction,
)