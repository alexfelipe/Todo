package br.com.alura.todo.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow

@ViewModelScoped
class AppStateViewModel : ViewModel() {

    val components: Components = Components

}

object Components {

    val appBar = MutableStateFlow(false)
    val bottomNavBar = MutableStateFlow(false)

}