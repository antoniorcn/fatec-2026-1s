package edu.curso

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavKey

class ContatoViewModel : ViewModel() {

    val backstack = mutableStateListOf<NavKey>(Rotas.Listagem)

}