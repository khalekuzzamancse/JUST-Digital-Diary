package queryservice.ui.event

interface SearchFunctionalityEvent {
    data class CallRequest(val number: String) : SearchFunctionalityEvent
    data class MessageRequest(val number: String) : SearchFunctionalityEvent
    data class EmailRequest(val email: String) : SearchFunctionalityEvent
    data object ExitRequest : SearchFunctionalityEvent

}