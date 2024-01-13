package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.just.cse.digital_diary.features.faculty.faculty.navigation.screen.FacultyModuleNavHost


@Composable
fun FacultyModule() {

    Navigator(
        screen = FacultyModuleNavHost()
    ){ navigator ->
        SlideTransition(navigator = navigator)
    }

}
