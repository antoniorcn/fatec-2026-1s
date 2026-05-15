package edu.curso.agendacontato.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.jose.jws.MacAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class JwtService (
  @Value("\${jwt.expiration}") private val jwtExpiration: Long,
    private val jwtEncoder : JwtEncoder
) {

    fun generateToken(authentication : Authentication) : String {
        val autoridades = authentication.authorities.joinToString(" ")
                            { it.authority.toString() }
        val username = authentication.name
        val expiracao = Instant.now().plusSeconds(jwtExpiration)

        val header = JwsHeader
                .with(MacAlgorithm.HS512)
                .build()

        val claims = JwtClaimsSet.builder()
            .issuedAt( Instant.now() )
            .expiresAt( expiracao )
            .issuer("agenda-contato-api")
            .subject(username)
            .claim("roles", autoridades)
            .build()

        return jwtEncoder.encode(
            JwtEncoderParameters.from(header, claims)
        ).tokenValue

    }

}