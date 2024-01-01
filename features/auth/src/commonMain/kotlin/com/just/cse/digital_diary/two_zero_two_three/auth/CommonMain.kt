package com.just.cse.digital_diary.two_zero_two_three.auth

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AuthModuleGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name! from auth module",
        modifier = modifier
    )
}