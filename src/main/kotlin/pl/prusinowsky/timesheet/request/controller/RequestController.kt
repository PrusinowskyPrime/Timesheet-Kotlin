package pl.prusinowsky.timesheet.request.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.project.service.ProjectService
import pl.prusinowsky.timesheet.request.service.RequestService
import pl.prusinowsky.timesheet.user.model.UserResponse
import pl.prusinowsky.timesheet.user.model.toResponse

@RestController
@RequestMapping("/api/v1/requests")
class RequestController @Autowired constructor(
    private val projectService: ProjectService,
    private val requestService: RequestService
) {

    @GetMapping("/project/{projectId}/sended")
    fun getPendingSendersByProjectId(@PathVariable projectId: String): ResponseEntity<List<UserResponse>> {
        val project = projectService.getById(projectId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val senders = requestService.getPendingSendersByProjectId(project.id)

        return ResponseEntity(senders.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/project/{projectId}/received")
    fun getPendingReceiversByProjectId(@PathVariable projectId: String): ResponseEntity<List<UserResponse>> {
        val project = projectService.getById(projectId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val receivers = requestService.getPendingReceiversByProjectId(project.id)

        return ResponseEntity(receivers.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/received")
    fun getPendingReceivers(): ResponseEntity<List<UserResponse>> {
        val receivers = requestService.getAllReceivers()

        return ResponseEntity(receivers.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/sended")
    fun getPendingSenders(): ResponseEntity<List<UserResponse>> {
        val senders = requestService.getAllSenders()

        return ResponseEntity(senders.map { it.toResponse() }, HttpStatus.OK)
    }
}