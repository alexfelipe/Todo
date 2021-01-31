package br.com.alura.todo.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.alura.todo.databinding.TasksListBinding
import br.com.alura.todo.model.Task
import br.com.alura.todo.ui.recyclerview.adapter.TasksListAdapter

class TasksListFragment : Fragment() {

    private var _binding: TasksListBinding? = null
    private val binding: TasksListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        TasksListBinding
            .inflate(
                inflater,
                container,
                false
            ).let {
                _binding = it
                it.root
            }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        Log.i("TasksListFragment", "onViewCreated: $_binding")
        binding.tasksListRecyclerview.adapter = TasksListAdapter(
            requireContext(),
            listOf(
                Task("task 1", "task 1"),
                Task("task 2", "task 2")
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}