package edu.curso

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun ContatoScreen( paddingValues : PaddingValues ) {
    val contatoVM = viewModel { ContatoViewModel() }
    NavDisplay(
        backStack = contatoVM.backstack,
        entryProvider = entryProvider {
            entry(Rotas.Listagem) { _ ->
                ContatoLista(contatoVM, paddingValues = paddingValues)
            }
            entry(Rotas.Formulario) { _ ->
                ContatoForm(contatoVM, paddingValues = paddingValues)
            }
        }
    )
}