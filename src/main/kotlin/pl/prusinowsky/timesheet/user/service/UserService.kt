package pl.prusinowsky.timesheet.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.user.entity.UserEntity
import pl.prusinowsky.timesheet.user.model.CreateUser
import pl.prusinowsky.timesheet.user.model.UpdateUser
import pl.prusinowsky.timesheet.user.repository.UserRepository

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {
    fun getAll(): List<UserEntity> = userRepository.findAll()

    fun getById(id: String): UserEntity? = userRepository.findById(id).orElse(null)

    fun create(data: CreateUser): UserEntity {
        val user = UserEntity(
            name = data.name,
            surname = data.surname,
            email = data.email,
            password = data.password
        )

        return userRepository.save(user)
    }

    fun update(id: String, updateData: UpdateUser): UserEntity? {
        val existingUser = userRepository.findById(id)

        if (!existingUser.isPresent) {
            return null
        }

        val user = existingUser.get()

        return userRepository.save(
            UserEntity(
                id = user.id,
                name = updateData.name,
                surname = updateData.surname,
                email = updateData.email,
                password = user.password
            )
        )
    }

    fun delete(id: String): Unit = userRepository.deleteById(id)
}