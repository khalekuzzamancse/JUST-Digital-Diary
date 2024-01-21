package com.just.cse.digital_diary.features.faculty.faculty

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.navigation.NavGraph
import com.just.cse.digital_diary.two_zero_two_three.department.DepartmentModuleEvent

/**
 * * This is Only the entry point to the Faculty module
 * * it will delegate to the NavGraph([NavGraph]) of this module.
 * @param onExitRequested :to exit from the Module
 *
 */
@Composable
fun FacultyModuleEntryPoint(
    event: FacultyModuleEvent,
    onExitRequested: () -> Unit,
) {
    NavGraph(
        onExitRequested = onExitRequested,
        event = DepartmentModuleEvent(
            onCallRequest = event.onCallRequest,
            onMessageRequest = event.onMessageRequest,
            onEmailRequest = event.onEmailRequest
        )
    )



}

