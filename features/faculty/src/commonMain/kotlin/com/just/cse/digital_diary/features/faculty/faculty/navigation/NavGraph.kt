package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator


@Composable
fun FacultyModule() {
    Navigator(
        screen = FacultyModuleNavHost()
    )

}
