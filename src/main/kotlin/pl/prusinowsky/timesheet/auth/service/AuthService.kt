package pl.prusinowsky.timesheet.auth.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.user.entity.UserEntity
import pl.prusinowsky.timesheet.user.service.UserService

@Service
class AuthService @Autowired constructor(
    private val userService: UserService
) {
    fun getCurrentUser(): UserEntity? {
        val authentication = SecurityContextHolder.getContext().authentication

        if (authentication != null && authentication.isAuthenticated) {
            val jwt = authentication.principal as Jwt

            return userService.getById(jwt.claims["userId"] as String)
        }

        return null
    }
}