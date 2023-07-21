package com.entalpiya.app.index.presentation.add_edit_task

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entalpiya.app.auth.domain.use_case.AuthUseCases
import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditTaskViewModel @Inject constructor(
    private val useCases: TaskUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    private val _state = mutableStateOf(AddEditTaskState())
    val state: State<AddEditTaskState> = _state

    private val _titleState = mutableStateOf(TextFieldState())
    val titleState: State<TextFieldState> = _titleState
    

    private val _descriptionState = mutableStateOf(TextFieldState())
    val descriptionState: State<TextFieldState> = _descriptionState

    init {
        getAndSetUser()
    }

    fun setTitle(value: String) {
        _titleState.value = _titleState.value.copy(text = value)
        _state.value = _state.value.copy(addTaskSuccess = value.isNotBlank())
        setTitleHintVisibility()
    }

    fun setDescription(value: String) {
        _descriptionState.value = _descriptionState.value.copy(text = value)
        setDescriptionHintVisibility()
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

    private fun setTitleHintVisibility() {
        _titleState.value = _titleState.value.copy(
            isHintVisible = _titleState.value.text.isBlank()
        )
    }
    private fun setDescriptionHintVisibility() {
        _descriptionState.value = _descriptionState.value.copy(
            isHintVisible = _descriptionState.value.text.isBlank()
        )
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

    private fun getAndSetUser() {
        viewModelScope.launch {
            val savedUserId = authUseCases.getUserId()
            if (savedUserId != null) {
                _state.value = _state.value.copy( userId = savedUserId )
            }
        }
    }
}