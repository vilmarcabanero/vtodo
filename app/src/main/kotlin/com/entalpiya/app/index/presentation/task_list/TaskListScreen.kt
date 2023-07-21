package com.entalpiya.app.index.presentation.task_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.core.presentation.components.FloatingAction
import com.entalpiya.app.index.presentation.destinations.AddEditTaskScreenDestination
import com.entalpiya.app.index.presentation.task_list.components.TaskItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun TaskListScreen(
    navigator: DestinationsNavigator,
    vm: TaskListViewModel = hiltViewModel(),
    task: Task? = null,
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        if (vm.state.value.hasDeleteAction && task != null) {
            scope.launch {
                val result = scaffoldState.snackbarHostState.showSnackbar(
                    message = "Task deleted",
                    actionLabel = "Undo"
                )
                if (result == SnackbarResult.ActionPerformed) {
                    vm.restoreTask(task)
                }

                vm.deleteHasDeleteAction()

//                Log.d("TodoListScreen", "In LaunchedEffect hasDeleteAction is $hasDeleteAction")
            }
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingAction(
                isEnabled = true,
                onClick = {
                    navigator.navigate(AddEditTaskScreenDestination(null))
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
                items(vm.state.value.tasks) { task ->
                    TaskItem(
                        task = task,
                        handleToggleIsComplete = {
                            vm.handleToggleCompleteTask(
                                task.id,
                                !task.isComplete
                            )
                        },
                        navigateToAddEditTaskScreen = {
                            navigator.navigate(AddEditTaskScreenDestination(task))
                        }
                    )
                }
            }
        }

    }
}