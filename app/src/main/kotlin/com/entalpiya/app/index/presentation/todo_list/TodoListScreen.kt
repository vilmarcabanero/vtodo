package com.entalpiya.app.index.presentation.todo_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.core.presentation.components.FloatingAction
import com.entalpiya.app.index.presentation.destinations.AddEditTodoScreenDestination
import com.entalpiya.app.index.presentation.todo_list.components.TodoItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun TodoListScreen(
    navigator: DestinationsNavigator,
    vm: TodoListViewModel = hiltViewModel(),
    hasDeleteAction: Boolean?,
    todo: Todo?
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        if (hasDeleteAction == true && todo != null) {
            scope.launch {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message = "Todo deleted",
                    actionLabel = "Undo"
                )
                if (result == SnackbarResult.ActionPerformed) {
                    vm.restoreTodo(todo)
                }
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingAction(
                onClick = {
                    navigator.navigate(AddEditTodoScreenDestination(null))
                },
                icon = Icons.Default.Add,
                desc = "add"
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(vm.state.value.todos) { todo ->
                    TodoItem(
                        id = todo.id,
                        title = todo.title,
                        description = todo.description,
                        isComplete = todo.isComplete,
                        handleToggleIsComplete = {
                            vm.handleToggleCompleteTodo(
                                todo.id,
                                !todo.isComplete
                            )
                        },
                        handleGetTodo = {
                            vm.handleGetTodo(todo.id)
                        },
                        navigateToAddEditTodoScreen = {
                            navigator.navigate(AddEditTodoScreenDestination(vm.state.value.selectedTodo))
                        }
                    )
                }
            }
        }

    }
}