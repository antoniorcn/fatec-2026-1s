package edu.curso.estoque.dto

import jakarta.validation.constraints.Email
import org.hibernate.validator.constraints.Length

data class CredenciaisDTO(
    @Email
    val email : String,
    @Length(min = 6, max = 30)
    val password : String
) {
}