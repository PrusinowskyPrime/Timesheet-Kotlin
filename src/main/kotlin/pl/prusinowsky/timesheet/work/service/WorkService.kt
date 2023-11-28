package pl.prusinowsky.timesheet.work.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.work.entity.WorkEntity
import pl.prusinowsky.timesheet.work.model.CreateWork
import pl.prusinowsky.timesheet.work.model.UpdateWork
import pl.prusinowsky.timesheet.work.repository.WorkRepository

@Service
class WorkService @Autowired constructor(
    private val workRepository: WorkRepository
) {
    fun getAll(): List<WorkEntity> = workRepository.findAll()

    fun getById(id: String): WorkEntity? = workRepository.findById(id).orElse(null)

    fun create(data: CreateWork): WorkEntity {
        return workRepository.save(
            WorkEntity(
                name = data.name,
                description = data.description,
                started = data.started,
                finished = data.finished
            )
        )
    }

    fun update(id: String, data: UpdateWork): WorkEntity? {
        val existingWork = workRepository.findById(id)

        if (!existingWork.isPresent) {
            return null
        }

        val work = existingWork.get()

        return workRepository.save(
            WorkEntity(
                id = work.id,
                name = data.name,
                description = data.description,
                started = data.started,
                finished = data.finished
            )
        )
    }

    fun delete(id: String): Unit = workRepository.deleteById(id)
}