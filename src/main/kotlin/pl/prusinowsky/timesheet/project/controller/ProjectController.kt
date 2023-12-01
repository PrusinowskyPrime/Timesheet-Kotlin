package pl.prusinowsky.timesheet.project.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.project.model.ProjectCreate
import pl.prusinowsky.timesheet.project.model.ProjectResponse
import pl.prusinowsky.timesheet.project.model.ProjectUpdate
import pl.prusinowsky.timesheet.project.model.toResponse
import pl.prusinowsky.timesheet.project.service.ProjectService
import pl.prusinowsky.timesheet.request.model.CreateRequest
import pl.prusinowsky.timesheet.request.model.RequestResponse
import pl.prusinowsky.timesheet.request.model.toResponse
import pl.prusinowsky.timesheet.request.service.RequestService
import pl.prusinowsky.timesheet.user.entity.UserEntity
import pl.prusinowsky.timesheet.user.service.UserService

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController @Autowired constructor(
    private val projectService: ProjectService,
    private val requestService: RequestService,
    private val userService: UserService,
) {
    @GetMapping
    fun getAllProjects(): ResponseEntity<List<ProjectResponse>> {
        val projects = projectService.getAll()

        return ResponseEntity(projects.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: String): ResponseEntity<ProjectResponse> {
        val project = projectService.getById(id)

        return if (project != null) {
            ResponseEntity(project.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createProject(@RequestBody project: ProjectCreate): ResponseEntity<ProjectResponse> {
        val createdProject = projectService.create(project)

        return ResponseEntity(createdProject.toResponse(), HttpStatus.CREATED)
    }

    @PostMapping("/{projectId}/requests")
    fun createJoinRequest(
        @PathVariable projectId: String,
        @RequestBody request: CreateRequest
    ): ResponseEntity<RequestResponse> {
        val project = projectService.getById(projectId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val sender: UserEntity = userService.getById(request.senderId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val receiver: UserEntity = userService.getById(request.receiverId)
            ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val createdRequest = requestService.create(project, sender, receiver)

        return ResponseEntity(createdRequest.toResponse(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: String, @RequestBody projectData: ProjectUpdate): ResponseEntity<ProjectResponse> {
        val project = projectService.update(id, projectData)

        return if (project != null) {
            ResponseEntity(project.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: String): ResponseEntity<Void> {
        projectService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}