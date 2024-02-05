package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.destination.FacultyDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.faculites.FacultyListDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
fun RootModule(appEvent: AppEvent) {
    AppTheme {
        AuthModuleEntryPoint(
            onLoginSuccess = {

            }
        )
//        FacultyListDestination(
//            event = FacultyDestinationEvent(
//                onDepartmentInfoRequest = {}
//            ),
//            onExitRequest = {},
//
//            )

    }
}



