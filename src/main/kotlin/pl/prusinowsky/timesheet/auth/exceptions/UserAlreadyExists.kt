package pl.prusinowsky.timesheet.auth.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.CONFLICT)
class UserAlreadyExists(message: String = "User with given data already exists") : RuntimeException(message)