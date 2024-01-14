package com.just.cse.digital_diary.features.faculty.faculty.navigation.child_destinations

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.departments.navgraph.DepartmentListModuleEntryPoint

class DepartmentListModuleEntryPoint(
    private val facultyId: String,
    private val onExitRequest: () -> Unit
): Screen {
    @Composable
    override fun Content() {
        DepartmentListModuleEntryPoint(
            facultyId=facultyId,
            onExitRequest = onExitRequest
        )
    }

}