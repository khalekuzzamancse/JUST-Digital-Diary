package navigation.hall.domain.model

data class ResetPasswordCodeValidateModel(
    val email:String,
    val code:String,
)