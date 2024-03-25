package miscellaneous.ui.route

/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
interface OtherFeatureFunctionalityEvent {
    data class CalenderRequest(val url:String): OtherFeatureFunctionalityEvent
}