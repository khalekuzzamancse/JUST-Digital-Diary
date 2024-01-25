package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.register.destination.states

data class RegisterDestinationState(
    val isLoading: Boolean,
    val isRegisterSuccess: Boolean,
    val message: String?,
) {
    companion object {
        fun toEmpty() = RegisterDestinationState(
            isLoading = false,
            message = null,
            isRegisterSuccess = false
        )
    }
}
