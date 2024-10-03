package navigation

sealed interface AppEvent {
    data class CallRequest(val number: String) : AppEvent
    data class MessageRequest(val number: String) : AppEvent
    data class EmailRequest(val email: String) : AppEvent
    data class WebVisitRequest(val url: String) : AppEvent
    data class LoginSuccess(val token: String) : AppEvent
    data object LogOut : AppEvent
}
