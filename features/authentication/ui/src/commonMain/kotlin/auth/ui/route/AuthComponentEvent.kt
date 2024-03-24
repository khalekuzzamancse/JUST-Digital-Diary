package auth.ui.route

interface AuthComponentEvent {
    data object LoginSuccess: AuthComponentEvent
    data object ExitRequest: AuthComponentEvent
}