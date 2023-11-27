package pl.prusinowsky.timesheet.user.model

data class CreateUser(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)
