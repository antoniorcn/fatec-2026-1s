package edu.curso.estoque.security

import edu.curso.estoque.model.Usuario
import edu.curso.estoque.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UsuarioSecurityService(
    val usuarioRepository: UsuarioRepository,
    val passwordEncoder: PasswordEncoder,
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

    fun compararSenha(senhaTexto: String, senhaCodificada: String) : Boolean {
        return passwordEncoder
            .matches(senhaTexto, senhaCodificada)
    }
}