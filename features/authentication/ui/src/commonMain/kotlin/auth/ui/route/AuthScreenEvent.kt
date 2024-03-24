package auth.ui.route

interface AuthScreenEvent {
    data class LoginSuccess(val username:String,val password:String) : AuthScreenEvent
    data object ExitRequest : AuthScreenEvent
}