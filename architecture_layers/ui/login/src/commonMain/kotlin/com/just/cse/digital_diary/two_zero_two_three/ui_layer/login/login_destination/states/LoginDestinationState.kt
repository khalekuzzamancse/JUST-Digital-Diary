package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states

data class LoginDestinationState(
    val isLoading: Boolean,
    val isLoginSuccess: Boolean,
    val message: String?,
) {
    companion object {
        fun toEmpty() = LoginDestinationState(
            isLoading = false,
            message = null,
            isLoginSuccess = false,
        )
    }
}
