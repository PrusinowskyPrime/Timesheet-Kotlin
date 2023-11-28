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
    fun getAllTasks(): List<TaskEntity> = taskRepository.findAll()

    fun getTaskById(id: String): TaskEntity? = taskRepository.findById(id).orElse(null)

    fun createTask(taskData: CreateTask): TaskEntity {
        val task = TaskEntity(
            name = taskData.name, description = taskData.description, isActive = taskData.isActive
        )

        return taskRepository.save(task)
    }

    fun updateTask(id: String, updatedTask: UpdateTask): TaskEntity? {
        val existingTask = taskRepository.findById(id)

        if (!existingTask.isPresent) {
            return null;
        }

        val task = existingTask.get()

        return taskRepository.save(
            TaskEntity(
                id = task.id,
                name = updatedTask.name,
                description = updatedTask.description,
                isActive = updatedTask.isActive
            )
        )
    }

    fun deleteTask(id: String): Unit = taskRepository.deleteById(id)
}