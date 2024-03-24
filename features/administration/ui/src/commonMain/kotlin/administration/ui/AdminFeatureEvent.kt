package administration.ui

/**
 * Used as convert or separate between the [AdminOfficesEvent] so that client module does not need to depends
 * on the lower module
 */
interface AdminFeatureEvent {
    data class CallRequest(val number: String) : AdminFeatureEvent
    data class MessageRequest(val number: String) : AdminFeatureEvent
    data class EmailRequest(val email: String) : AdminFeatureEvent
}