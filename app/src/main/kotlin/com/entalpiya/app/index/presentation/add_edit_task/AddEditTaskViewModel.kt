package com.entalpiya.app.index.presentation.add_edit_task

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val useCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(AddEditTaskState())
    val state: State<AddEditTaskState> = _state


    private val _titleState = mutableStateOf("")
    val titleState: State<String> = _titleState

    private val _descriptionState = mutableStateOf("")
    val descriptionState: State<String> = _descriptionState


    fun setTitle(value: String) {
        _titleState.value = value
        _state.value = _state.value.copy(addTaskSuccess = value.isNotBlank())
    }

    fun setDescription(value: String) {
        _descriptionState.value = value
    }

    fun setHasDeleteAction(value: Boolean) {
        _state.value = _state.value.copy(hasDeleteAction = value)
    }

    fun setToggleOpenPopup() {
        _state.value = _state.value.copy(isPopUpOpen = !_state.value.isPopUpOpen)
    }

    fun setClosePopup() {
        _state.value = _state.value.copy(isPopUpOpen = false)
    }

    fun insertTask(task: Task) {
        viewModelScope.launch {
            useCases.insertTask(task)
        }
    }

    fun saveHasDeleteAction() {
        viewModelScope.launch {
            useCases.saveHasDeleteAction()
        }
    }
}