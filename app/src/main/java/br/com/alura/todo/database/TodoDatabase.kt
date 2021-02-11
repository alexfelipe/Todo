package br.com.alura.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alura.todo.database.dao.TaskDao
import br.com.alura.todo.model.Task


@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

}