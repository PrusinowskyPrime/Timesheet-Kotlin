package pl.prusinowsky.timesheet.user.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.user.entity.UserEntity
import java.util.Optional

interface UserRepository : MongoRepository<UserEntity, String> {
    fun findByEmail(email: String): Optional<UserEntity>
}