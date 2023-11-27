package pl.prusinowsky.timesheet.user.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("users")
data class UserEntity(
    @Id
    val id: String = ObjectId.get().toString(),
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)