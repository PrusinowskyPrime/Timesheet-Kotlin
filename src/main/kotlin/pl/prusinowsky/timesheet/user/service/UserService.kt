package pl.prusinowsky.timesheet.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.user.entity.UserEntity
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

    fun createUser(user: UserEntity): UserEntity {
        return userRepository.save(user)
    }

    fun updateUser(id: String, updatedUser: UserEntity): UserEntity? {
        val existingUser = userRepository.findById(id)
        if (existingUser.isPresent) {
            val user = existingUser.get()
            return userRepository.save(user)
        }

        return null
    }

    fun deleteUser(id: String) {
        userRepository.deleteById(id)
    }
}