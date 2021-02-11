package br.com.alura.todo.repository

import br.com.alura.todo.database.dao.TaskDao
import br.com.alura.todo.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {

    fun findAll(): Flow<List<Task>> = dao.findAll()

    suspend fun save(task: Task) = dao.save(task)

}