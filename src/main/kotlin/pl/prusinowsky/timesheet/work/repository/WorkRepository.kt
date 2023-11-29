package pl.prusinowsky.timesheet.work.repository

import org.springframework.data.mongodb.repository.MongoRepository
import pl.prusinowsky.timesheet.work.entity.WorkEntity

interface WorkRepository : MongoRepository<WorkEntity, String>