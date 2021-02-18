package br.com.alura.todo.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import br.com.alura.todo.R
import br.com.alura.todo.databinding.TaskFormBinding
import br.com.alura.todo.model.Task
import br.com.alura.todo.viewmodel.AppStateViewModel
import br.com.alura.todo.viewmodel.Components
import br.com.alura.todo.viewmodel.TaskFormViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TaskFormFragment : Fragment() {

    private var _binding: TaskFormBinding? = null
    private val binding: TaskFormBinding get() = _binding!!
    private val viewModel: TaskFormViewModel by viewModels()
    private val appState by activityViewModels<AppStateViewModel>()
    private val navController by lazy {
        findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            appState.setComponents(Components(toolbar = true))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = TaskFormBinding.inflate(
        inflater,
        container,
        false
    ).let {
        _binding = it
        it.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.task_form_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_task -> save()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {
        val task = createTask()
        lifecycleScope.launch {
            viewModel.save(task)
            navController.popBackStack()
        }
    }

    private fun createTask(): Task {
        val title = binding.taskFormTitle.text.toString()
        val description = binding.taskFormDescription.text.toString()
        return Task(
            title = title,
            description = description
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}