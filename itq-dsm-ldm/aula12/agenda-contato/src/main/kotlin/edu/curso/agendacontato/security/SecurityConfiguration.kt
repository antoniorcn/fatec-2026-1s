package edu.curso.agendacontato.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration (
    val userDetailsService: UserDetailsService
) {
    @Bean
    fun cadastrarFiltro() : FilterRegistrationBean<FiltroA> {
        println("*** FILTRO A FOI CADASTRADO ***")
        val registration = FilterRegistrationBean<FiltroA>()
        registration.setFilter(FiltroA())
        registration.order = 1
        return registration
    }

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun getAuthenticationManager(authConfiguration : AuthenticationConfiguration) :
            AuthenticationManager {
        return authConfiguration.authenticationManager
    }

    @Bean
    fun getAuthenticationProvider(passEncoder : PasswordEncoder) : AuthenticationProvider {
        val provider = DaoAuthenticationProvider( userDetailsService )
        provider.setPasswordEncoder(passEncoder)
        return provider
    }

    @Bean
    fun secureFilterChain(http : HttpSecurity,
                          provider : AuthenticationProvider) : SecurityFilterChain {
        return http
            .csrf{ it.disable() }
            .sessionManagement{
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(provider)
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/auth/**").permitAll()
                    .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            }
            .build()
    }
}