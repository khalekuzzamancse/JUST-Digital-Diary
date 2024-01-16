package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.edit_profile.EditProfileScreen

@Composable
fun EditProfileEntryPoint(
    onExitRequest:()->Unit,
) {
    EditProfileScreen(
        onExitRequest = onExitRequest
    )

}