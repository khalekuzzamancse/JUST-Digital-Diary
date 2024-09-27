package auth.domain.model

data class AccountVerifyModel(
    val username:String,
    val otp:String,
)
