package pl.prusinowsky.timesheet.user.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.user.entity.UserEntity
import pl.prusinowsky.timesheet.user.model.CreateUser
import pl.prusinowsky.timesheet.user.model.UpdateUser
import pl.prusinowsky.timesheet.user.model.UserResponse
import pl.prusinowsky.timesheet.user.model.toResponse
import pl.prusinowsky.timesheet.user.service.UserService

@RestController
@RequestMapping("/api/v1/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {
    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val users = userService.getAllUsers()

        return ResponseEntity(users.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: String): ResponseEntity<UserResponse> {
        val user = userService.getUserById(id)

        return if (user != null) {
            ResponseEntity(user.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createUser(@RequestBody user: CreateUser): ResponseEntity<UserResponse> {
        val createdUser = userService.createUser(user)

        return ResponseEntity(createdUser.toResponse(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: String, @RequestBody userUpdate: UpdateUser): ResponseEntity<UserResponse> {
        val user = userService.updateUser(id, userUpdate)

        return if (user != null) {
            ResponseEntity(user.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: String): ResponseEntity<Void> {
        userService.deleteUser(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}