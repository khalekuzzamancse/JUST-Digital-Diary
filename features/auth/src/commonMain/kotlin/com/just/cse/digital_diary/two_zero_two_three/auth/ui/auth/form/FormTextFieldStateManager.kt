package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.form

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow




class FormTextFieldStateManager(
    fieldState: FormTextFieldState,
    private val observeTrailingIconClick: ((FormTextFieldState) -> FormTextFieldState)? = null,
    var validator: ((String) -> String?)? = null
) {
    private val _state = MutableStateFlow(fieldState)
    val state = _state.asStateFlow()
    fun onTextChange(text: String) {
        _state.value = _state.value.copy(value = text)
    }


    fun validate() {
        validator?.let {
            val errorMessage = it(_state.value.value)
            _state.value = _state.value.copy(errorMessage = errorMessage)
        }
    }

    fun onTrailingIconClick() {
        observeTrailingIconClick?.let {
            _state.value = it(_state.value)
        }
    }
}