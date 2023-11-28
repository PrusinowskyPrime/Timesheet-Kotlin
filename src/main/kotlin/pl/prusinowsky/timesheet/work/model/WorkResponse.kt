package pl.prusinowsky.timesheet.work.model

import pl.prusinowsky.timesheet.work.entity.WorkEntity
import java.util.*

data class WorkResponse(
    val id: String,
    val name: String,
    val description: String,
    val started: Date,
    val finished: Date
)

fun WorkEntity.toResponse() = WorkResponse(
    id = id,
    name = name,
    description = description,
    started = started,
    finished = finished
)
