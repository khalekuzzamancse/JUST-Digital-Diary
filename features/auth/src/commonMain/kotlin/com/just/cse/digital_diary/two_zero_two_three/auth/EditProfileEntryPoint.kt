package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile.EditProfileDestination

@Composable
fun EditProfileEntryPoint(
    onExitRequest:()->Unit,
) {
    EditProfileDestination(
        onExitRequest = onExitRequest
    )

}