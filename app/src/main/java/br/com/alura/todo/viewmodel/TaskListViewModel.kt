package br.com.alura.todo.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.todo.model.Task
import br.com.alura.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TaskListViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    fun findAllTasks(): Flow<List<Task>> =
        repository.findAll()

}