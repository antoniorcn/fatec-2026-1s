package edu.curso.agendacontato.controller

import edu.curso.agendacontato.dto.UsuarioDto
import edu.curso.agendacontato.model.Usuario
import edu.curso.agendacontato.security.JwtService
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
    private val passwordEncoder : PasswordEncoder,
    private val jwtService: JwtService
) {

    @PostMapping("/signin")
    fun signIn(@RequestBody usuarioDto : UsuarioDto) : ResponseEntity<String> {
        val objAuth = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                usuarioDto.username, usuarioDto.password
            )
        )
        val token = jwtService.generateToken(objAuth)
        println("Authenticated - Token : $token")
        return ResponseEntity.ok(token)
    }

    @PostMapping("/signup")
    fun signUp(@RequestBody usuario : Usuario) : ResponseEntity<String> {
        val senhaCrypto = passwordEncoder.encode(usuario.senha)
        if (senhaCrypto != null) {
            val userEncoded = usuario.copy( senha = senhaCrypto )
            userDetailService.cadastrar(userEncoded)
            return ResponseEntity.ok("Usuario Registrado")
        } else {
            return ResponseEntity.badRequest().body("Erro ao registrar o usuario")
        }
    }

}