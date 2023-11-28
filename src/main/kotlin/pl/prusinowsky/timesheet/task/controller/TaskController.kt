package pl.prusinowsky.timesheet.task.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.task.model.CreateTask
import pl.prusinowsky.timesheet.task.model.TaskResponse
import pl.prusinowsky.timesheet.task.model.UpdateTask
import pl.prusinowsky.timesheet.task.model.toResponse
import pl.prusinowsky.timesheet.task.service.TaskService

@RestController
@RequestMapping("/api/v1/tasks")
class TaskController @Autowired constructor(
    private val taskService: TaskService
) {
    @GetMapping
    fun getAllTasks(): ResponseEntity<List<TaskResponse>> {
        val tasks = taskService.getAllTasks()

        return ResponseEntity(tasks.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: String): ResponseEntity<TaskResponse> {
        val task = taskService.getTaskById(id)

        return if (task != null) {
            ResponseEntity(task.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createTask(@RequestBody task: CreateTask): ResponseEntity<TaskResponse> {
        val createdTask = taskService.createTask(task)

        return ResponseEntity(createdTask.toResponse(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: String, @RequestBody taskUpdate: UpdateTask): ResponseEntity<TaskResponse> {
        val task = taskService.updateTask(id, taskUpdate)

        return if (task != null) {
            ResponseEntity(task.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: String): ResponseEntity<Void> {
        taskService.deleteTask(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}