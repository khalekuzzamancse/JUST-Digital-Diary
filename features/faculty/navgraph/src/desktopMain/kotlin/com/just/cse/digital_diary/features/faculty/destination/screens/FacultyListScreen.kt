package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator

internal class FacultyListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
//        FacultiesScreen(
//            onTeacherListRequest = { deptId ->
//                navigator?.push(
//                    TeacherListScreen(
//                        deptId = deptId,
//                        onExitRequest = {
//                            navigator.pop()
//                        }
//                    )
//                )
//            }
//        )

    }
}