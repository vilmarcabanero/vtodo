package com.entalpiya.app.index.presentation.add_edit_todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.entalpiya.app.core.presentation.components.FloatingAction
import com.entalpiya.app.core.utils.hideSoftwareKeyboard
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.presentation.destinations.TodoListScreenDestination
import com.entalpiya.app.index.presentation.todo_list.TodoListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.util.UUID

@Destination
@Composable
fun AddEditTodoScreen(
    navigator: DestinationsNavigator,
    vm: AddEditTodoViewModel = hiltViewModel(),
    todoListVm: TodoListViewModel = hiltViewModel(),
    todo: Todo?
) {
    val scaffoldState = rememberScaffoldState()
    val focusRequester = remember { FocusRequester() }
    val view = LocalView.current

    LaunchedEffect(Unit) {
        if (todo == null) {
            focusRequester.requestFocus()
        } else {
            vm.setTitle(todo.title)
            vm.setDescription(todo.description ?: "")
        }
    }

    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                todo?.let {
                    IconButton(onClick = { vm.setToggleOpenPopup() }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                        DropdownMenu(
                            expanded = vm.state.value.isPopUpOpen,
                            onDismissRequest = { vm.setClosePopup() },
                            modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2f)
                        ) {
                            DropdownMenuItem(onClick = {
                                todoListVm.handleDeleteTodo(todo.id)
                                vm.setHasDeleteAction(true)
                                navigator.navigate(
                                    TodoListScreenDestination(
                                        todo = todo
                                    )
                                )
                                vm.saveHasDeleteAction()
                            }) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingAction(
                onClick = {
                    handleAddEditTodo(vm, todo, navigator)
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
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    hideSoftwareKeyboard(view)
                    handleAddEditTodo(vm, todo, navigator)
                })
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
                    .padding(10.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    hideSoftwareKeyboard(view)
                    handleAddEditTodo(vm, todo, navigator)
                })
            )
        }
    }
}

fun handleAddEditTodo(vm: AddEditTodoViewModel, todo: Todo?, navigator: DestinationsNavigator) {
    if (vm.state.value.addTodoSuccess) {
        vm.insertTodo(
            Todo(
                id = todo?.id ?: UUID.randomUUID().toString(),
                title = vm.titleState.value,
                description = vm.descriptionState.value,
                isComplete = todo?.isComplete ?: false,
                createdAt = todo?.createdAt ?: System.currentTimeMillis(),
            )
        )
        navigator.navigate(
            TodoListScreenDestination(
                todo = null
            )
        )
    }
}