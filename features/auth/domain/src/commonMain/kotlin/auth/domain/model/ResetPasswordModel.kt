package auth.domain.model

data class ResetPasswordModel(
    val email:String,
    val code:String,
    val newPassword:String
)
