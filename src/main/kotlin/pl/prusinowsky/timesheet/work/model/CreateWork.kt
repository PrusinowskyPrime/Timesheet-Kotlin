package pl.prusinowsky.timesheet.work.model

import java.util.*


data class CreateWork(
    val name: String,
    val description: String,
    val started: Date,
    val finished: Date
)