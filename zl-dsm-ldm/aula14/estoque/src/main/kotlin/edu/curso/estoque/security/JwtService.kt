package edu.curso.estoque.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jose.jws.JwsAlgorithm
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.stereotype.Service
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import java.time.Instant

@Service
class JwtService(
    @Value("\${jwt.expiration}") private val expirationTime : Long,
    private val jwtEncoder : JwtEncoder
) {
    fun generateToken( authentication : Authentication): String {

        val now = Instant.now()

        val authorities = authentication.authorities.joinToString(" ") {
            it : GrantedAuthority -> it.authority.toString()
        }

        val claims = JwtClaimsSet.builder()
            .issuer("sistema-estoque")
            .issuedAt( now )
            .expiresAt( now.plusMillis(expirationTime ) )
            .subject(authentication.name.toString())
            .claim("roles", authorities)
        .build()

        val header = JwsHeader.with(MacAlgorithm.HS512).build()

        return jwtEncoder.encode(
            JwtEncoderParameters.from(header, claims))
            .tokenValue
    }
}