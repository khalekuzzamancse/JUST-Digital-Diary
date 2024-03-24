package administration.navgraph.event

/**
 * Used as convert or separate between the  so that client module does not need to depends
 * on the lower module
 */
interface AdminEvent {
    data class CallRequest(val number: String) : AdminEvent
    data class MessageRequest(val number: String) : AdminEvent
    data class EmailRequest(val email: String) : AdminEvent
    data object ExitRequest: AdminEvent
}