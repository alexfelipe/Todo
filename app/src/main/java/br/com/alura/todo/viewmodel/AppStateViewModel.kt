package br.com.alura.todo.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

@ViewModelScoped
class AppStateViewModel : ViewModel() {

    private val _components: MutableStateFlow<Components> = MutableStateFlow(Components())
    val components: Flow<Components> = _components

    suspend fun setComponents(components: Components) {
        _components.emit(components)
    }

}

class Components(
    val toolbar: Boolean = false,
    val bottomNavBar: Boolean = false
)