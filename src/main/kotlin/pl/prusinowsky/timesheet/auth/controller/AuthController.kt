package pl.prusinowsky.timesheet.auth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import pl.prusinowsky.timesheet.auth.exceptions.LoginFailed
import pl.prusinowsky.timesheet.auth.exceptions.UserAlreadyExists
import pl.prusinowsky.timesheet.auth.model.LoginData
import pl.prusinowsky.timesheet.auth.model.LoginResponse
import pl.prusinowsky.timesheet.auth.service.HashService
import pl.prusinowsky.timesheet.auth.service.TokenService
import pl.prusinowsky.timesheet.auth.model.RegisterUser
import pl.prusinowsky.timesheet.user.model.UserResponse
import pl.prusinowsky.timesheet.user.model.toResponse
import pl.prusinowsky.timesheet.user.service.UserService

@RestController
@RequestMapping("/api/v1/auth")
class AuthController @Autowired constructor(
    private val hashService: HashService,
    private val tokenService: TokenService,
    private val userService: UserService,
) {
    @PostMapping("/login")
    fun login(@RequestBody payload: LoginData): ResponseEntity<LoginResponse> {
        val user = userService.getByEmail(payload.email) ?: throw UserAlreadyExists()

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw LoginFailed()
        }

        return ResponseEntity(
            LoginResponse(
                tokenType = "Bearer",
                accessToken = tokenService.createAccessToken(user),
                refreshToken = tokenService.createRefreshToken(user)
            ), HttpStatus.OK
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterUser): ResponseEntity<UserResponse> {
        if (userService.getByEmail(request.email) !== null) {
            throw UserAlreadyExists()
        }

        return ResponseEntity(userService.create(request).toResponse(), HttpStatus.CREATED)
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<String> {
        SecurityContextHolder.clearContext()

        return ResponseEntity("Logout successful", HttpStatus.OK)
    }
}