package br.com.alura.todo.viewmodel

import androidx.lifecycle.ViewModel
import br.com.alura.todo.model.Task
import br.com.alura.todo.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class TaskFormViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    suspend fun save(task: Task) {
        repository.save(task)
    }

}
