package edu.curso.agendacontato.security

import edu.curso.agendacontato.model.Usuario
import edu.curso.agendacontato.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsuarioSecurityService(
    val usuarioRepository: UsuarioRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val usuario = usuarioRepository.findByEmail(username)
        if (usuario != null) {
            return UserDetailImplementation(usuario)
        } else {
            throw RuntimeException("Usuário não encontrado")
        }
    }

    fun cadastrar( usuario : Usuario) {
        usuarioRepository.save(usuario)
    }

}