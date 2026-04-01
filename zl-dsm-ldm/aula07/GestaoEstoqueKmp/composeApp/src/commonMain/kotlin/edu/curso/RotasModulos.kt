package edu.curso

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface RotasModulos : NavKey {
    @Serializable
    object Estoque : RotasModulos
    @Serializable
    object Contatos : RotasModulos
}