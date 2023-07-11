package com.entalpiya.app.index.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.presentation.components.FloatingAction
import com.entalpiya.app.index.presentation.components.TodoItem
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun IndexScreen(vm: IndexViewModel = hiltViewModel()) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        floatingActionButton = {
            FloatingAction(
                navigateTo = { println() },
                icon = Icons.Default.Add,
                desc = "add"
            )
        },
        scaffoldState = scaffoldState
    ) {
        val list = listOf(
            Todo("1", "Todo 1", null, false),
            Todo("2", "Todo 2", "This is todo 2", false)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                        handleDelete = {
                            vm.handleDeleteTodo(todo.id)
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
                    )
                }
            }
        }
    }
}