package pl.prusinowsky.timesheet.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.user.entity.UserEntity
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService @Autowired constructor(
    private val jwtEncoder: JwtEncoder,
) {
    fun createAccessToken(user: UserEntity): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()

        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(15L, ChronoUnit.MINUTES)) // Adjust expiration as needed
            .subject(user.name)
            .claim("userId", user.id)
            .claim("tokenType", "access")
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun createRefreshToken(user: UserEntity): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()

        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.name)
            .claim("userId", user.id)
            .claim("tokenType", "refresh")
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }
}