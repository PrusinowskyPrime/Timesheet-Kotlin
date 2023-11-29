package pl.prusinowsky.timesheet.task.model

import pl.prusinowsky.timesheet.task.entity.TaskEntity

data class TaskResponse(
    val id: String,
    val name: String,
    val description: String,
    val isActive: Boolean,
)

fun TaskEntity.toResponse() = TaskResponse(
    id = id,
    name = name,
    description = description,
    isActive = isActive,
)