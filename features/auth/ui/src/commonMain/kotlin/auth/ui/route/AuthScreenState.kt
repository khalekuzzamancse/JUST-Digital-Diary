package auth.ui.route

data class AuthScreenState(
    val showProgressBar: Boolean=false,
    val snackBarMessage: String?=null,
    val showRegisterForm: Boolean=false
)
