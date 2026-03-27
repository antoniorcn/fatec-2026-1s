package edu.curso.estoque

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface RotaEstoque : NavKey {
    @Serializable
    object Lista : RotaEstoque
    @Serializable
    object Formulario : RotaEstoque
}