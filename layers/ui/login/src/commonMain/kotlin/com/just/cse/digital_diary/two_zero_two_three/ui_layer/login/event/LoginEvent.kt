package com.just.cse.digital_diary.two_zero_two_three.ui_layer.login.event
/**
 * * Wrapping the event hierarchy within a single interface so that after some time
 * it is easy to find which event is   occurs,it it is easy to access the event name from the
 * client code or module.
 * * Refactor it if needed
 * * Available Events
 *
 */
 interface LoginEvent {
  interface LoginDestinationEvent: LoginEvent {
   data class LoginSuccess(val username: String, val password: String) : LoginDestinationEvent
  }
  interface LoginControlsEvent: LoginEvent {
   data object LoginRequest : LoginControlsEvent
   data object PasswordResetRequest : LoginControlsEvent
   data object RegisterRequest : LoginControlsEvent
  }
}