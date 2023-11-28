package pl.prusinowsky.timesheet.work.model

import java.util.*

data class UpdateWork(
    val name: String,
    val description: String,
    val started: Date,
    val finished: Date
)
