package edu.curso.agendacontato.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

// Data Transfer Object - DTO
data class ContatoDto (
    @NotEmpty(message = "O nome deve ser preenchido")
    @Size(message="O nome não deve ser maior que 100", max = 100)
    val nome : String = "",
    @NotEmpty(message = "O telefone deve ser preenchido")
    @Size(min=8, max = 30,
        message="O numero do telefone deve possuir entre 8 e 30 caracteres")
    val telefone : String = "",
    @NotEmpty(message = "O email deve ser preenchido")
    @Email @Size(min = 5, max=100,
        message="O email deve conter entre 5 e 100 caracteres")
    val email : String = ""
) {

}