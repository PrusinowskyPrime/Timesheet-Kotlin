package pl.prusinowsky.timesheet.user.model

import pl.prusinowsky.timesheet.user.entity.UserEntity

data class UserResponse(
    val id: String,
    val name: String,
    val surname: String,
    val email: String
)

fun UserEntity.toResponse() = UserResponse(
    id = id,
    name = name,
    surname = surname,
    email = email
)