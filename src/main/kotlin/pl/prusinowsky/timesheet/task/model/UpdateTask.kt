package pl.prusinowsky.timesheet.task.model

data class UpdateTask(
    val name: String,
    val description: String,
    val isActive: Boolean,
)