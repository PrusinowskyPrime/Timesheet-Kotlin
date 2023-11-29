package pl.prusinowsky.timesheet.project.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.project.model.ProjectCreate
import pl.prusinowsky.timesheet.project.model.ProjectUpdate
import pl.prusinowsky.timesheet.project.repository.ProjectRepository

@Service
class ProjectService @Autowired constructor(
    private val projectRepository: ProjectRepository
) {
    fun getAll(): List<ProjectEntity> = projectRepository.findAll()

    fun getById(id: String): ProjectEntity? = projectRepository.findById(id).orElse(null)

    fun create(data: ProjectCreate): ProjectEntity {
        val project = ProjectEntity(
            name = data.name,
            description = data.description
        )

        return projectRepository.save(project)
    }

    fun update(id: String, updateData: ProjectUpdate): ProjectEntity? {
        val existingProject = projectRepository.findById(id)

        if (!existingProject.isPresent) {
            return null
        }

        val project = existingProject.get()

        return projectRepository.save(
            ProjectEntity(
                id = project.id,
                name = updateData.name,
                description = updateData.description
            )
        )
    }

    fun deleteProject(id: String): Unit = projectRepository.deleteById(id)
}