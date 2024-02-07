package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.faculty.destination.TeacherListDestination


class TeacherListScreen(
    private val deptId: String,
    private val exitRequest:()->Unit
) : Screen {
    @Composable
    override fun Content() {
        TeacherListDestination(
            deptId = deptId,
            onExitRequest = exitRequest
        )
    }
}