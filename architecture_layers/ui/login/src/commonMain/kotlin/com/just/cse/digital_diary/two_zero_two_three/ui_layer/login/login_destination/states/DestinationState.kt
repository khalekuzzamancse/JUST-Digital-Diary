package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.states

data class DestinationState(
    val isLoading: Boolean,
    val isLoginSuccess: Boolean,
    val message: String?,
) {
    companion object {
        fun toEmpty() = DestinationState(
            isLoading = false,
            message = null,
            isLoginSuccess = false
        )
    }
}
