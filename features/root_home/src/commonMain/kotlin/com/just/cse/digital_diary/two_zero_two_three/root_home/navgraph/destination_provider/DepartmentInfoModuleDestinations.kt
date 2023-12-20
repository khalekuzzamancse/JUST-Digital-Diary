package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEvent
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

object DepartmentInfoModuleDestinations {
    val DEPARTMENT_INFO=Destination.createDestination("DEPARTMENT_INFO")
    @Composable
    fun DepartmentInfo(
        departmentId: String,
        appEvent: AppEvent,
        onExitRequest: () -> Unit,
    ) {
        DepartmentModuleEntryPoint(
            departmentId = departmentId,
            event = DepartmentModuleEvent(
                onCallRequest = appEvent.onCallRequest,
                onWebsiteViewRequest = appEvent.onWebsiteViewRequest,
                onMessageRequest = appEvent.onMessageRequest,
                onEmailRequest = appEvent.onEmailRequest
            ),
            onExitRequested = onExitRequest
        )
    }

}
