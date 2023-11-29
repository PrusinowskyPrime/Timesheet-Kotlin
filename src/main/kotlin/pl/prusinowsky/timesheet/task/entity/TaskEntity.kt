package pl.prusinowsky.timesheet.task.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tasks")
data class TaskEntity(
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val description: String,
    val isActive: Boolean,
)