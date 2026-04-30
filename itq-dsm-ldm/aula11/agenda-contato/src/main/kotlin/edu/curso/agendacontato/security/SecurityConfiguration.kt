package edu.curso.agendacontato.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class SecurityConfiguration(
    val userDetailsService: UserDetailsService
) {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    fun configure(auth : AuthenticationManagerBuilder) {
        try {
            auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }


}