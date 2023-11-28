package pl.prusinowsky.timesheet.work.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import pl.prusinowsky.timesheet.work.model.CreateWork
import pl.prusinowsky.timesheet.work.model.UpdateWork
import pl.prusinowsky.timesheet.work.model.WorkResponse
import pl.prusinowsky.timesheet.work.model.toResponse
import pl.prusinowsky.timesheet.work.service.WorkService

@RestController
@RequestMapping("/api/v1/works")
class WorkController @Autowired constructor(
    private val workService: WorkService
) {
    @GetMapping
    fun getAllWorks(): ResponseEntity<List<WorkResponse>> {
        val works = workService.getAll()

        return ResponseEntity(works.map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getWorkById(@PathVariable id: String): ResponseEntity<WorkResponse> {
        val work = workService.getById(id)

        return if (work != null) {
            ResponseEntity(work.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun createWork(@RequestBody work: CreateWork): ResponseEntity<WorkResponse> {
        val createdWork = workService.create(work)

        return ResponseEntity(createdWork.toResponse(), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateWork(@PathVariable id: String, @RequestBody workUpdate: UpdateWork): ResponseEntity<WorkResponse> {
        val work = workService.update(id, workUpdate)

        return if (work != null) {
            ResponseEntity(work.toResponse(), HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteWork(@PathVariable id: String): ResponseEntity<Void> {
        workService.delete(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}