package br.com.alura.todo.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.alura.todo.databinding.TasksListBinding
import br.com.alura.todo.ui.recyclerview.adapter.TasksListAdapter
import br.com.alura.todo.viewmodel.AppStateViewModel
import br.com.alura.todo.viewmodel.Components
import br.com.alura.todo.viewmodel.TaskListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TasksListFragment : Fragment() {

    private var _binding: TasksListBinding? = null
    private val binding: TasksListBinding get() = _binding!!
    private val viewModel: TaskListViewModel by viewModels()
    private val appState by activityViewModels<AppStateViewModel>()
    private val navController by lazy {
        findNavController()
    }

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
        lifecycleScope.launch {
            appState.setComponents(Components())
        }
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            viewModel.findAllTasks().collect { tasksFound ->
                binding.tasksListRecyclerview.adapter = TasksListAdapter(
                    requireContext(),
                    tasksFound
                )
            }
        }
        binding.tasksListFab.setOnClickListener {
            TasksListFragmentDirections
                .actionTasksListToTaskForm()
                .let(navController::navigate)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}