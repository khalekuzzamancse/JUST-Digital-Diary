package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.states

data class DestinationState(
    val formData: FormData,
    val isLoading: Boolean,
    val message: String?,
) {
    companion object {
        fun toEmpty() = DestinationState(
            formData = FormData(username = "", password = ""),
            isLoading = false,
            message = null
        )
    }
}
