package pl.prusinowsky.timesheet.project.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.project.repository.ProjectRepository

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository
) {
    fun getAllProjects(): List<ProjectEntity> {
        return projectRepository.findAll()
    }

    fun getProjectById(id: String): ProjectEntity? {
        return projectRepository.findById(id).orElse(null)
    }

    fun createProject(project: ProjectEntity): ProjectEntity {
        return projectRepository.save(project)
    }

    fun updateProject(id: String, updatedProject: ProjectEntity): ProjectEntity? {
        val existingProject = projectRepository.findById(id)
        if (existingProject.isPresent) {
            val project = existingProject.get()
            return projectRepository.save(project)
        }

        return null
    }

    fun deleteProject(id: String) {
        projectRepository.deleteById(id)
    }
}