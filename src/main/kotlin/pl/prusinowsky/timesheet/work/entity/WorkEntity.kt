package pl.prusinowsky.timesheet.work.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.Date

@Document("works")
data class WorkEntity(
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val description: String,
    val started: Date,
    val finished: Date
)