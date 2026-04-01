package edu.curso

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun ProdutoScreen( produtoVM : ProdutoViewModel, paddingValues : PaddingValues ) {
    NavDisplay(
        backStack = produtoVM.backstack,
        entryProvider = entryProvider {
            entry(Rotas.Listagem) { _ ->
                ProdutoLista(produtoVM, paddingValues = paddingValues)
            }
            entry(Rotas.Formulario) { _ ->
                ProdutoForm(produtoVM, paddingValues = paddingValues)
            }
        }
    )
}