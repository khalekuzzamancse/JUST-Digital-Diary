package miscellaneous

/**
 * It is a separate to prevent public low level event,that should not be care about the client module
 */
sealed interface MiscFeatureEvent {
    data class CalenderRequest(val url:String): MiscFeatureEvent
    data object NavigateToFacultyList: MiscFeatureEvent
    data object NavigateTAdminOfficeList: MiscFeatureEvent
}
