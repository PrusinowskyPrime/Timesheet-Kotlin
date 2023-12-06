package pl.prusinowsky.timesheet.auth.model

data class LoginResponse(
    val tokenType: String,
    val accessToken: String,
    val refreshToken: String
)
