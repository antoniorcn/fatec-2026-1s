package edu.curso.estoque.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
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
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    @Value("\${jwt.secret}") private val secretKey : String
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
    fun securityFilterChain(http: HttpSecurity,
                            authenticationProvider : AuthenticationProvider): SecurityFilterChain {
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
            .authenticationProvider(authenticationProvider)
            .oauth2ResourceServer { oauth2 -> oauth2.jwt{} }
            .build()
    }

    @Bean
    fun jwtEncoder() : JwtEncoder {
        val secretHash = SecretKeySpec(secretKey.toByteArray(),
            MacAlgorithm.HS512.name)

        val jwk = OctetSequenceKey.Builder( secretHash ).build()
        val jwkSource = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))
        return NimbusJwtEncoder(jwkSource)
    }

    @Bean
    fun jwtDecoder() : JwtDecoder {
        val secretHash = SecretKeySpec(secretKey.toByteArray(),
            MacAlgorithm.HS512.name)
        return NimbusJwtDecoder
            .withSecretKey(secretHash)
            .macAlgorithm(MacAlgorithm.HS512)
            .build()
    }

}