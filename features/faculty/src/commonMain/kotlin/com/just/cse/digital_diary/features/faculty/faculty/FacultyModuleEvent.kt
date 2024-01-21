package com.just.cse.digital_diary.features.faculty.faculty

data class FacultyModuleEvent(
    val onCallRequest:(String)->Unit={},
    val onMessageRequest:(String)->Unit={},
    val onEmailRequest:(String)->Unit={},
    val onWebsiteViewRequest: (String)->Unit={},
    val onExitRequested: () -> Unit={},
)