package com.entalpiya.app.index.domain.use_case

data class TodoUseCases(
    val getAllTodos: GetAllTodos,
    val insertTodo: InsertTodo,
    val deleteTodo: DeleteTodo,
    val toggleCompleteTodo: ToggleCompleteTodo,
    val getTodo: GetTodo,
    val getHasDeleteAction: GetHasDeleteAction,
    val saveHasDeleteAction: SaveHasDeleteAction,
    val deleteHasDeleteAction: DeleteHasDeleteAction,
)