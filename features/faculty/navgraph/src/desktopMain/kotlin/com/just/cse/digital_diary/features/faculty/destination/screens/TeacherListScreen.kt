package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen


internal class TeacherListScreen(
    private val deptId: String,
    private val onExitRequest: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
//        TeachersScreen(
//            deptId = deptId,
//            onExitRequest = onExitRequest
//        )
    }
}