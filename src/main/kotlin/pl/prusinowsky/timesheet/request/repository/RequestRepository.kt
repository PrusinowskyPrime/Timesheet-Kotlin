package pl.prusinowsky.timesheet.request.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.request.entity.RequestEntity

interface RequestRepository : MongoRepository<RequestEntity, String> {
    fun findByProjectIdAndStatus(projectId: String, status: String): List<RequestEntity>
}