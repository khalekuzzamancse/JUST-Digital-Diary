package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile

import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.login.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditProfileViewModel(

) {
    private val scope = CoroutineScope(Dispatchers.Default)

    companion object {
        private const val TAG = "EditProfileViewModel: "
        private fun log(message: String) {
            println("$TAG$message")
        }
    }

    private val _data = MutableStateFlow(
        EditFormData(
            name = "Mr Bean",
            username = "abc 123",
            dept = "ME"
        )
    )
    val data = _data.asStateFlow()

    fun onFullNameChanged(fullName: String) {
        _data.update { it.copy(name = fullName) }
    }


    fun onUsernameChanged(username: String) {
        _data.update { it.copy(username = username) }
    }

    fun onDeptChanged(dept: String) {
        _data.update { it.copy(dept = dept) }
    }


    private val _showProcessBar = MutableStateFlow(false)
    val showProcessBar = _showProcessBar.asStateFlow()
    private val _screenMessage = MutableStateFlow<String?>(null)
    val snackBarMessage = _screenMessage.asStateFlow()
    private fun updateScreenMessage(msg: String) {
        scope.launch {
            _screenMessage.value = msg
            delay(1000)
            clearScreenMessage()
        }
    }


    private fun clearScreenMessage() {
        _screenMessage.value = null
    }

    init {
        scope.launch {
            snackBarMessage.collect { msg ->
                if (msg != null) {
                    LoginViewModel.log(msg)

                }
            }
        }
    }

    fun onEditRequest() {
        scope.launch {
            log("${data.value}")
            _showProcessBar.value = true
            delay(2000) //pretend for network IO
            _showProcessBar.value = false
            updateScreenMessage("Not Implemented Yet...")
        }

    }
}
