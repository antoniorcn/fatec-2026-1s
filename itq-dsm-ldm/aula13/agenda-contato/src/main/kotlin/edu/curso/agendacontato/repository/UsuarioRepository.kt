package edu.curso.agendacontato.repository

import edu.curso.agendacontato.model.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {

    fun findByEmail(email: String) : Usuario?

}