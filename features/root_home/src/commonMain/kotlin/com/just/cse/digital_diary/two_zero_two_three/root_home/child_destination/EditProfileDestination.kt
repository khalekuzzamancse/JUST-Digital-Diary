package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.auth.EditProfileEntryPoint

@Composable
fun EditProfile(
    onExitRequest: () -> Unit
) {
    EditProfileEntryPoint(
        onExitRequest = onExitRequest
    )

}