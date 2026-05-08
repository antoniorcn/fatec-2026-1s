package edu.curso.agendacontato.controller

import edu.curso.agendacontato.dto.UsuarioDto
import edu.curso.agendacontato.model.Usuario
import edu.curso.agendacontato.security.UsuarioSecurityService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authenticationManager : AuthenticationManager,
    private val userDetailService : UsuarioSecurityService,
    private val passwordEncoder : PasswordEncoder
) {

    @PostMapping("/signin")
    fun signIn(@RequestBody usuarioDto : UsuarioDto) : ResponseEntity<String> {
        val objAuth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                usuarioDto.username, usuarioDto.password
            )
        )
        println("Authenticated - Authentication Obj : ${objAuth}")
        return ResponseEntity.ok("Authenticado")
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody usuario : Usuario) : ResponseEntity<String> {
        val senha = passwordEncoder.encode(usuario.senha)
        if (senha != null) {
            val userEncoded = usuario.copy( senha = senha )
            userDetailService.cadastrar(userEncoded)
            return ResponseEntity.ok("Usuario Registrado")
        } else {
            return ResponseEntity.badRequest().body("Erro ao registrar o usuario")
        }
    }

}