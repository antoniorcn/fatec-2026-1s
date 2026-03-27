package edu.curso.estoque

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay

@Composable
fun EstoqueScreen( vm : GestaoEstoqueViewModel, padding : PaddingValues)  {
    NavDisplay(
        backStack = vm.backstack.value,
        entryProvider = entryProvider<NavKey> {
            entry(RotaEstoque.Lista) {
                EstoqueLista(vm, padding)
            }
            entry(RotaEstoque.Formulario) {
                EstoqueFormulario(vm, padding)
            }
        }
    )
}