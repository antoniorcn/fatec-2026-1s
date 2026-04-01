package edu.curso

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Rotas : NavKey {
    @Serializable
    object Listagem : Rotas
    @Serializable
    object Formulario : Rotas
}