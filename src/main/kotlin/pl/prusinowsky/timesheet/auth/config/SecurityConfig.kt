package pl.prusinowsky.timesheet.auth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.web.SecurityFilterChain
import pl.prusinowsky.timesheet.auth.service.TokenService

@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
    private val tokenService: TokenService
) {
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { csrf -> csrf.disable() } // Remove when develop (it is only for testing)
            .authorizeHttpRequests { authorizeHttpRequest ->
                authorizeHttpRequest
                .requestMatchers("/api/v1/auth/register").permitAll()
                .requestMatchers("/api/v1/auth/login").permitAll()
                .anyRequest().authenticated()
            }

            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()) }
            }

        return http.build()
    }


    private fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtConverter = JwtAuthenticationConverter()

        jwtConverter.setJwtGrantedAuthoritiesConverter { jwt ->
            val authorities = jwt.getClaimAsStringList("permissions") ?: emptyList()
            authorities.map { SimpleGrantedAuthority(it) }
        }

        return jwtConverter
    }
}

