package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.destination.FacultyListDestination

class FacultyListScreen(
    private val onExitRequest: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        FacultyListDestination(
            onExitRequest = onExitRequest,
            onEmptyRequest = { deptId ->
                navigator?.push(
                    TeacherListScreen(
                        deptId = deptId,
                        exitRequest = {
                            navigator.pop()
                        }
                    )
                )
            }
        )
    }
}