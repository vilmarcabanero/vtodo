package com.entalpiya.app.index.presentation.add_edit_todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.entalpiya.app.core.presentation.components.FloatingAction
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.presentation.destinations.TodoListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.UUID

@Destination
@Composable
fun AddEditTodoScreen(
    navigator: DestinationsNavigator,
    vm: AddEditTodoViewModel = hiltViewModel(),
    todo: Todo?
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true) {
        todo?.let {
            vm.setTitle(it.title)
            vm.setDescription(it.description ?: "")
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingAction(
                onClick = {
                    if (vm.addTodoSuccess.value) {
                        vm.insertTodo(
                            Todo(
                                id = todo?.id ?: UUID.randomUUID().toString(),
                                title = vm.titleState.value,
                                description = vm.descriptionState.value,
                                isComplete = false
                            )
                        )
                        navigator.navigate(TodoListScreenDestination())
                    }
                },
                icon = Icons.Default.Check,
                desc = "check"
            )
        },
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            TextField(
                value = vm.titleState.value,
                onValueChange = { title -> vm.setTitle(title) },
                placeholder = { Text(text = "Title") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            TextField(
                value = vm.descriptionState.value,
                onValueChange = { desc -> vm.setDescription(desc) },
                placeholder = { Text(text = "Description (optional)") },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.background
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}