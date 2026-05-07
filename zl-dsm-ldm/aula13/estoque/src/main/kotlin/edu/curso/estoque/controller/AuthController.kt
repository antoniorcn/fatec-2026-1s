package edu.curso.estoque.controller

import edu.curso.estoque.dto.CredenciaisDTO
import edu.curso.estoque.dto.UsuarioDTO
import edu.curso.estoque.model.Usuario
import edu.curso.estoque.security.UsuarioSecurityService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.security.authentication.AuthenticationManager
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userService : UsuarioSecurityService,
    private val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/signin")
    fun signin(@Valid @RequestBody credenciais : CredenciaisDTO) :
            ResponseEntity<String>
    {
        try {
            val passwordEncoded = passwordEncoder.encode(credenciais.password)
            val authObj = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    credenciais.email, passwordEncoded
                )
            )
            print("Authentication Object $authObj")
        } catch (e : Exception) {
            e.printStackTrace()
        }
        return ResponseEntity.ok("Login efetuado com sucesso")
    }

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody usuarioDTO : UsuarioDTO) :
            ResponseEntity<String>
    {
        val passwordEncoded = passwordEncoder.encode(usuarioDTO.password)
        return if (passwordEncoded != null) {
            val usuario = Usuario(
                0L, nome = usuarioDTO.nome, email = usuarioDTO.email,
                senha = passwordEncoded, perfil = usuarioDTO.perfil
            )
            userService.cadastrar(usuario)

            val authObj = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    usuarioDTO.email, passwordEncoded
                )
            )
            ResponseEntity.ok("Registro efetuado com sucesso")
        } else {
            ResponseEntity.badRequest().body("Erro ao codificar a senha")
        }
    }

}
