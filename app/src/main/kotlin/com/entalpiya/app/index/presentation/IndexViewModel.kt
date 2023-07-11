package com.entalpiya.app.index.presentation

import android.util.Log
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
class IndexViewModel @Inject constructor(
    private val useCases: TodoUseCases
) : ViewModel() {
    private val _state = mutableStateOf(IndexState())
    val state: State<IndexState> = _state

    init {
        getAndSetAllTodos()
    }

    private fun getAndSetAllTodos() {
        viewModelScope.launch {
            val todos = useCases.getAllTodos()
            _state.value = _state.value.copy(todos = todos)
        }
    }

    fun handleToggleCompleteTodo(id: String, isComplete: Boolean) {
        viewModelScope.launch {
            useCases.toggleCompleteTodo(id, isComplete)
            getAndSetAllTodos()
        }
    }

    fun handleDeleteTodo(id: String) {
        viewModelScope.launch {
            useCases.deleteTodo(id)
            getAndSetAllTodos()
        }
    }

    fun restoreTodo(todo: Todo) {
        viewModelScope.launch {
            useCases.insertTodo(todo)
            getAndSetAllTodos()
        }
    }
}