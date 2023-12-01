package pl.prusinowsky.timesheet.request.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import pl.prusinowsky.timesheet.project.entity.ProjectEntity
import pl.prusinowsky.timesheet.request.entity.RequestEntity
import pl.prusinowsky.timesheet.request.enum.RequestStatus
import pl.prusinowsky.timesheet.request.enum.toLower
import pl.prusinowsky.timesheet.request.repository.RequestRepository
import pl.prusinowsky.timesheet.user.entity.UserEntity

@Service
class RequestService @Autowired constructor(
    private val requestRepository: RequestRepository,
) {
    fun create(project: ProjectEntity, sender: UserEntity, receiver: UserEntity): RequestEntity {
        val request = RequestEntity(
            project = project,
            sender = sender,
            receiver = receiver,
        )

        return requestRepository.save(request)
    }

    fun getPendingSendersByProjectId(projectId: String): Set<UserEntity> {
        val requests = requestRepository.findByProjectIdAndStatus(projectId, RequestStatus.PENDING.toLower())

        return requests.map { it.sender }.toSet()
    }

    fun getPendingReceiversByProjectId(projectId: String): Set<UserEntity> {
        val requests = requestRepository.findByProjectIdAndStatus(projectId, RequestStatus.PENDING.toLower())

        return requests.map { it.receiver }.toSet()
    }

    fun getAllSenders(): Set<UserEntity> {
        val requests = requestRepository.findAll()

        return requests.map { it.sender }.toSet()
    }

    fun getAllReceivers(): Set<UserEntity> {
        val requests = requestRepository.findAll()

        return requests.map { it.receiver }.toSet()
    }
}