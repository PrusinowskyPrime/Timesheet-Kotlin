package pl.prusinowsky.timesheet.project.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("projects")
data class ProjectEntity(
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val description: String,
)