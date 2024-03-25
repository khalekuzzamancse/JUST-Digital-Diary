package miscellaneous.ui
/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
interface OtherFeatureEvent {
    data class CalenderRequest(val url:String):OtherFeatureEvent
}