package com.entalpiya.app.index.presentation.add_edit_todo

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entalpiya.app.index.domain.model.Todo
import com.entalpiya.app.index.domain.use_case.TodoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val useCases: TodoUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AddEditTodoState())
    val state: State<AddEditTodoState> = _state


    private val _titleState = mutableStateOf("")
    val titleState: State<String> = _titleState

    private val _descriptionState = mutableStateOf("")
    val descriptionState: State<String> = _descriptionState


    fun setTitle(value: String) {
        _titleState.value = value
        _state.value = _state.value.copy(addTodoSuccess = value.isNotBlank())
    }

    fun setDescription(value: String) {
        _descriptionState.value = value
    }



    fun setToggleOpenPopup() {
        _state.value = _state.value.copy(isPopUpOpen = !_state.value.isPopUpOpen)
    }

    fun setClosePopup() {
        _state.value = _state.value.copy(isPopUpOpen = false)
    }

    fun insertTodo(todo: Todo) {
        viewModelScope.launch {
            useCases.insertTodo(todo)
        }
    }
}