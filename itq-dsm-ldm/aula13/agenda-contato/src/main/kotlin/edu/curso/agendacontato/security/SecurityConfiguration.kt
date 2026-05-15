package edu.curso.agendacontato.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.OctetSequenceKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.annotation.Value
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
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
class SecurityConfiguration (
    private val userDetailsService: UserDetailsService,
     @Value("\${jwt.secret}")
     private val secretKey : String
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
            .oauth2ResourceServer { oauth2 -> oauth2.jwt{ } }
            .build()
    }

    @Bean
    fun encoderJwt() : JwtEncoder {
        val secretHash = SecretKeySpec(
            secretKey.toByteArray(),
            MacAlgorithm.HS512.name
        )
        val jwk = OctetSequenceKey.Builder(secretHash).build()
        val jwkSource = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))
        return NimbusJwtEncoder(jwkSource)
    }

    @Bean
    fun decoderJwt() : JwtDecoder {
        val secretHash = SecretKeySpec(
            secretKey.toByteArray(),
            MacAlgorithm.HS512.name
        )
        return NimbusJwtDecoder
            .withSecretKey(secretHash)
            .macAlgorithm(MacAlgorithm.HS512)
            .build()
    }
}