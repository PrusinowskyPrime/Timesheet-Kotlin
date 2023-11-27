package pl.prusinowsky.timesheet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class TimesheetApplication

fun main(args: Array<String>) {
    runApplication<TimesheetApplication>(*args)
}
