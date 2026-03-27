package edu.curso.estoque

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface RotaPrincipal : NavKey {
    @Serializable
    object Cliente : RotaPrincipal
    @Serializable
    object Estoque : RotaPrincipal
}