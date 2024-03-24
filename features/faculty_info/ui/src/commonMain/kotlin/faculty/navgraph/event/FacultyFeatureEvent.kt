package faculty.navgraph.event

    /**
     * Used as convert or separate between the [FacultyEvent] so that client module does not need to depends
     * on the lower module
     */
   sealed interface FacultyFeatureEvent {
        data class CallRequest(val number: String) : FacultyFeatureEvent
        data class MessageRequest(val number: String) : FacultyFeatureEvent
        data class EmailRequest(val email: String) : FacultyFeatureEvent
        data object ExitRequest: FacultyFeatureEvent
    }
