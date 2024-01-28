package com.just.cse.digital_diary.features.faculty.faculty

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.faculty_list.FacultiesScreen
import com.just.cse.digital_diary.features.faculty.faculty.event.FacultyModuleEvent

/**
 * * This is Only the entry point to the Faculty module
 * * it will delegate to the NavGraph([NavGraph]) of this module.
 * @param onExitRequest :to exit from the Module
 *
 */
@Composable
fun FacultyModuleEntryPoint(
    event: FacultyModuleEvent,
    onExitRequest: () -> Unit={},
) {
    FacultiesScreen(
        onEmployeeListRequest = event.onDepartmentInfoRequest,
        onExitRequest=onExitRequest
    )

}

