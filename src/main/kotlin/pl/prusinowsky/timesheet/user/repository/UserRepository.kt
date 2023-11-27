package pl.prusinowsky.timesheet.user.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.user.entity.UserEntity

interface UserRepository : MongoRepository<UserEntity, String>