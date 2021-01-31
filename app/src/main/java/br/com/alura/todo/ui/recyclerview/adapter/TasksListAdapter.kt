package br.com.alura.todo.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.todo.databinding.TaskItemBinding
import br.com.alura.todo.model.Task

class TasksListAdapter(private val context: Context, tasks: List<Task> = listOf()) :
    RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {

    private val dataset = tasks.toMutableList()

    class ViewHolder(private val binding: TaskItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.taskTitle.text = task.title
            binding.taskDescription.text = task.description
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder = ViewHolder(
        TaskItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = dataset[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = dataset.size

}
