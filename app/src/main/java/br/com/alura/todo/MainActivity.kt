package br.com.alura.todo

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import br.com.alura.todo.databinding.ActivityMainBinding
import br.com.alura.todo.viewmodel.AppStateViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
    private val appState: AppStateViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)

        val navHostFragment =
            supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController

        configureComponents()
    }

    private fun configureComponents() {
        lifecycleScope.launchWhenCreated {
            appState.components.collect { components ->
                binding.bottomNavigationView.visibility =
                    getViewVisibility(components.bottomNavBar)
                binding.toolbar.visibility =
                    getViewVisibility(components.toolbar)
            }
        }
    }

    private fun getViewVisibility(visible: Boolean): Int {
        if (visible) {
            return VISIBLE
        }
        return GONE
    }
}