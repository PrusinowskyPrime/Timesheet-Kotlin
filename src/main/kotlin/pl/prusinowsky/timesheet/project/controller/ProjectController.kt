package pl.prusinowsky.timesheet.project.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.project.service.ProjectService

@RestController
@RequestMapping("/api/v1/projects")
class ProjectController @Autowired constructor(
    private val projectService: ProjectService
) {
    @GetMapping
    fun getAllProjects(): ResponseEntity<List<ProjectEntity>> {
        val projects = projectService.getAllProjects()
        return ResponseEntity(projects, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getProjectById(@PathVariable id: String): ResponseEntity<ProjectEntity> {
        val project = projectService.getProjectById(id)
        return if (project != null) {
            ResponseEntity(project, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createProject(@RequestBody project: ProjectEntity): ResponseEntity<ProjectEntity> {
        val createdProject = projectService.createProject(project)
        return ResponseEntity(createdProject, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateProject(@PathVariable id: String, @RequestBody updatedProject: ProjectEntity): ResponseEntity<ProjectEntity> {
        val project = projectService.updateProject(id, updatedProject)
        return if (project != null) {
            ResponseEntity(project, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteProject(@PathVariable id: String): ResponseEntity<Void> {
        projectService.deleteProject(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}