package pl.prusinowsky.timesheet.request.model

data class CreateRequest(
    val senderId: String,
    val receiverId: String
)