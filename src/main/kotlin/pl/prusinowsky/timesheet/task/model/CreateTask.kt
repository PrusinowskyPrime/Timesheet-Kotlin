package pl.prusinowsky.timesheet.task.model

data class CreateTask(
    val name: String,
    val description: String,
    val isActive: Boolean,
)