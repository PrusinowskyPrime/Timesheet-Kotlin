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
    fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    fun getUserById(id: String): UserEntity? {
        return userRepository.findById(id).orElse(null)
    }

    fun createUser(user: CreateUser): UserEntity {
        val entity = UserEntity(
            name = user.name,
            surname = user.surname,
            email = user.email,
            password = user.password
        )

        return userRepository.save(entity)
    }

    fun updateUser(id: String, updateData: UpdateUser): UserEntity? {
        val existingUser = userRepository.findById(id)

        if (existingUser.isPresent) {
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

        return null
    }

    fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }
}