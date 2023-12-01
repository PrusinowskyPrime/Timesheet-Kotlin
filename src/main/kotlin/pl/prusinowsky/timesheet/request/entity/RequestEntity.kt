package pl.prusinowsky.timesheet.request.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.request.enum.RequestStatus
import pl.prusinowsky.timesheet.request.enum.toLower
import pl.prusinowsky.timesheet.user.entity.UserEntity
import java.util.Date

@Document("requests")
data class RequestEntity(
    @Id
    val id: String = ObjectId.get().toString(),
    @DBRef
    val project: ProjectEntity,
    @DBRef
    val sender: UserEntity,
    @DBRef
    val receiver: UserEntity,
    val status: String = RequestStatus.PENDING.toLower(),
    val changed: Boolean = false,
    val createdAt: Date = Date()
)