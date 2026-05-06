package edu.curso.estoque.security

import edu.curso.estoque.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailImplementation(
    val usuario : Usuario
) : UserDetails {

    override fun getPassword(): String {
        return usuario.senha
    }

    override fun getUsername(): String {
        return usuario.email
    }

    override fun getAuthorities(): Collection<out GrantedAuthority> {
        val perfis = usuario.perfil.split(",")
        val lista = perfis.map { GrantedAuthority { "$it " } }
        return lista
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}