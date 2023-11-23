package pl.prusinowsky.timesheet.project.model

import pl.prusinowsky.timesheet.project.entity.ProjectEntity

data class ProjectResponse(
    val id: String,
    val name: String,
    val description: String,
)


fun ProjectEntity.toResponse() = ProjectResponse(
    id = id,
    name = name,
    description = description,
)
