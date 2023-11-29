package pl.prusinowsky.timesheet.task.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.task.repository.TaskRepository
import pl.prusinowsky.timesheet.task.entity.TaskEntity
import pl.prusinowsky.timesheet.task.model.CreateTask
import pl.prusinowsky.timesheet.task.model.UpdateTask

@Service
class TaskService @Autowired constructor(
    private val taskRepository: TaskRepository
) {
    fun getAll(): List<TaskEntity> = taskRepository.findAll()

    fun getById(id: String): TaskEntity? = taskRepository.findById(id).orElse(null)

    fun create(data: CreateTask): TaskEntity {
        val task = TaskEntity(
            name = data.name, description = data.description, isActive = data.isActive
        )

        return taskRepository.save(task)
    }

    fun update(id: String, data: UpdateTask): TaskEntity? {
        val existingTask = taskRepository.findById(id)

        if (!existingTask.isPresent) return null

        val task = existingTask.get()

        return taskRepository.save(
            TaskEntity(
                id = task.id,
                name = data.name,
                description = data.description,
                isActive = data.isActive
            )
        )
    }

    fun delete(id: String): Unit = taskRepository.deleteById(id)
}