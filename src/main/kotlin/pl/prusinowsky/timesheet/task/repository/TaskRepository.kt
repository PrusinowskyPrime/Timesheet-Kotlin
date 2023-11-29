package pl.prusinowsky.timesheet.task.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.task.entity.TaskEntity

interface TaskRepository : MongoRepository<TaskEntity, String>