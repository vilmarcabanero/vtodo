package com.entalpiya.app.index.presentation.task_list

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.entalpiya.app.auth.domain.use_case.AuthUseCases
import com.entalpiya.app.core.utils.toISOString
import com.entalpiya.app.index.domain.model.Task
import com.entalpiya.app.index.domain.use_case.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val useCases: TaskUseCases,
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    private val _state = mutableStateOf(TaskListState())
    val state: State<TaskListState> = _state

    init {
        getAndSetAllTasks()
        getAndSetHasDeleteAction()
        getAndSetUser()
    }

    private fun setHasDeleteAction(value: Boolean) {
        _state.value = _state.value.copy(hasDeleteAction = value)
    }

    private fun getAndSetAllTasks() {
        viewModelScope.launch {
            val todos = useCases.getAllTasks()
            _state.value = _state.value.copy(tasks = todos)
        }
    }

    fun handleToggleCompleteTask(id: String, isComplete: Boolean) {
        viewModelScope.launch {
            useCases.toggleCompleteTask(id, isComplete)
            getAndSetAllTasks()
        }
    }

    fun handleDeleteTask(id: String) {
        viewModelScope.launch {
            useCases.deleteTask(id)
            getAndSetAllTasks()
        }
    }

    fun restoreTask(task: Task) {
        viewModelScope.launch {
            useCases.insertTask(task)
            getAndSetAllTasks()
        }
    }

    private fun getAndSetHasDeleteAction() {
        viewModelScope.launch {
            val hasDeleteAction = useCases.getHasDeleteAction() == "true"
            setHasDeleteAction(hasDeleteAction)
            Log.d(
                "TaskListViewModel",
                "getAndSetHasDeleteAction() hasDeleteAction is $hasDeleteAction"
            )
        }
    }

    fun deleteHasDeleteAction() {
        viewModelScope.launch {
            useCases.deleteHasDeleteAction()
            setHasDeleteAction(false)
        }
    }

    private fun getAndSetUser() {
        viewModelScope.launch {
            val savedUserId = authUseCases.getUserId()
            if (savedUserId == null) {
                val userId = UUID.randomUUID().toString()
                val userCreatedAt = toISOString(Date())
                authUseCases.saveUserId(userId)
                authUseCases.saveUserCreatedAt(userCreatedAt)
            }
        }
    }
}