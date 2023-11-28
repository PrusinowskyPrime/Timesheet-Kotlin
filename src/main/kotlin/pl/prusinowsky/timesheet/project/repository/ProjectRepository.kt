package pl.prusinowsky.timesheet.project.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.project.entity.ProjectEntity

interface ProjectRepository : MongoRepository<ProjectEntity, String>