package com.just.cse.digital_diary.features.faculty.faculty.navigation.child_destinations

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEvent

internal class DepartmentModule(
    private val departmentId: String,
   private val event: DepartmentModuleEvent,
    private val onExitRequest: () -> Unit
): Screen {
    @Composable
    override fun Content() {
        DepartmentModuleEntryPoint(
            departmentId=departmentId,
            onExitRequested = onExitRequest,
            event = event
        )
    }

}