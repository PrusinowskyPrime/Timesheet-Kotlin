package pl.prusinowsky.timesheet.auth.model

data class RegisterUser(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)
