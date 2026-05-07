package edu.curso.estoque.dto

import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length


data class UsuarioDTO(
    val nome : String,
    @Email
    val email : String,
    @Length(min = 6, max = 30, message = "A senha deve conter no mínimo 6 caracteres")
    val password : String,
    @Length(max = 30, message = "O Perfil deve conter no maximo 30 caracteres")
    val perfil : String
) {
    constructor() : this("", "", "", "")
}