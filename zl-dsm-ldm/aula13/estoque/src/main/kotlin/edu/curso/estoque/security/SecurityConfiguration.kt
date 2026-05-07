package edu.curso.estoque.security

import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService
) {

    @Bean
    fun passwordEncoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun getAuthenticationManager(authConfiguration : AuthenticationConfiguration)
        : AuthenticationManager
    {
        println("***** AuthenticationManager PRODUZIDO *******")
        return authConfiguration.authenticationManager
    }

    @Bean
    fun authenticationProvider() : AuthenticationProvider {
        println("***** AuthenticationProvider PRODUZIDO *******")
        val provider = DaoAuthenticationProvider(userDetailsService)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }


    @Bean
    fun cadastroFiltro(): FilterRegistrationBean<MeuFiltro> {
        val registrationBean = FilterRegistrationBean<MeuFiltro>()
        registrationBean.setFilter(MeuFiltro())
//        registrationBean.addUrlPatterns("/*") // Set specific URL paths
        registrationBean.setOrder(1) // Set the execution order
        println("*** FILTRO MEUFILTRO Registrado ***")
        return registrationBean
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { csrf -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/auth/**").permitAll()
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            }
            .sessionManagement {
                session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider())
            .build()
    }
}