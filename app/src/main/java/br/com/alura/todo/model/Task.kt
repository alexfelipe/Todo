package br.com.alura.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Task(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String
)
