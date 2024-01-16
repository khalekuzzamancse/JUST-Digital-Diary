package com.just.cse.digital_diary.features.departments.navgraph.child_destinations

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEntryPoint

internal class DepartmentModule(
    private val departmentId: String,
    private val onExitRequest: () -> Unit
): Screen {
    @Composable
    override fun Content() {
        DepartmentModuleEntryPoint(
            departmentId=departmentId,
            onExitRequested = onExitRequest
        )
    }

}