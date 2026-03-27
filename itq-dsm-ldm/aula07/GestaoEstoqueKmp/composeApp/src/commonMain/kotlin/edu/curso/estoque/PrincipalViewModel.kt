package edu.curso.estoque

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

class PrincipalViewModel : ViewModel() {

    val backstack = mutableStateOf<NavBackStack<NavKey>>(
        NavBackStack(RotaPrincipal.Cliente)
    )

    fun navigateTo( rota : NavKey ) {
        backstack.value.clear()
        backstack.value.add( rota )
    }


}