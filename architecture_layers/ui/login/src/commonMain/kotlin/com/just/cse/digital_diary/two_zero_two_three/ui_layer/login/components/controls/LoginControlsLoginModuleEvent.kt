package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.components.controls

import com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.login_destination.event.LoginModuleEvent

 interface LoginControlsLoginModuleEvent:LoginModuleEvent{
    data object LoginModuleRequest : LoginControlsLoginModuleEvent
    data object PasswordResetRequest : LoginControlsLoginModuleEvent
    data object RegisterRequest : LoginControlsLoginModuleEvent
}