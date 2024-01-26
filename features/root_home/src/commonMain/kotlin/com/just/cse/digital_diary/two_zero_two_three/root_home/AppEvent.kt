package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable

data class AppEvent(
    val onCallRequest:(String)->Unit={},
    val onMessageRequest:(String)->Unit={},
    val onEmailRequest:(String)->Unit={},
    val onWebsiteViewRequest: (String)->Unit={},
)