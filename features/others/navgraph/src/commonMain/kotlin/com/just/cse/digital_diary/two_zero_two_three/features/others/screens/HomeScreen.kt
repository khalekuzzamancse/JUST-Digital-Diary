package com.just.cse.digital_diary.two_zero_two_three.features.others.screens

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.features.others.event.OtherFeatureFunctionalityEvent
import com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities.Home

@Composable
fun HomeScreen(
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
    onEvent:(OtherFeatureFunctionalityEvent)->Unit,
) {
    Home(
        universityLogo = universityLogo,
        university = university,
        onEvent = onEvent,
    )


}