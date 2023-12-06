package pl.prusinowsky.timesheet.auth.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class BadCredentials() : RuntimeException("Bad credentials")