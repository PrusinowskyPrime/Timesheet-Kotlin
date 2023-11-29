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

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController @Autowired constructor(
    private val projectService: ProjectService
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