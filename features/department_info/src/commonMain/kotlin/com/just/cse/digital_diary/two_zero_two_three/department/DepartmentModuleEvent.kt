package com.just.cse.digital_diary.two_zero_two_three.department

data class DepartmentModuleEvent(
    val onCallRequest:(String)->Unit={},
    val onMessageRequest:(String)->Unit={},
    val onEmailRequest:(String)->Unit={},
    val onWebsiteViewRequest: (String)->Unit={},
    val onExitRequested: () -> Unit={},
)
