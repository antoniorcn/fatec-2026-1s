package edu.curso.estoque

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Rota : NavKey {
    @Serializable
    object Lista : Rota
    @Serializable
    object Formulario : Rota
}