package pl.prusinowsky.timesheet.request.model

import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.request.entity.RequestEntity
import pl.prusinowsky.timesheet.user.entity.UserEntity
import java.util.Date

data class RequestResponse(
    val id: String,
    val project: ProjectEntity,
    val sender: UserEntity,
    val receiver: UserEntity,
    val status: String,
    val changed: Boolean,
    val createdAt: Date
)

fun RequestEntity.toResponse() = RequestResponse(
    id = id,
    project = project,
    sender = sender,
    receiver = receiver,
    status = status,
    changed = changed,
    createdAt = createdAt
)