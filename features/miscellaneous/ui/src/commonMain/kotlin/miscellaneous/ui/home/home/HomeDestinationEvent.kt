package miscellaneous.ui.home.home

interface HomeDestinationEvent {
    data class CalenderViewRequest(val url:String) : HomeDestinationEvent
    data object NavigationRequest : HomeDestinationEvent

}