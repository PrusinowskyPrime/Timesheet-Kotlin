package pl.prusinowsky.timesheet.request.enum

import java.util.Locale

enum class RequestStatus() {
    ACCEPTED,
    PENDING,
    DECLINED
}

fun RequestStatus.toLower(): String {
    return this.name.lowercase(Locale.getDefault())
}