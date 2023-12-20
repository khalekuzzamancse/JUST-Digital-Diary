package com.just.cse.digital_diary.features.faculty.faculty

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties.FacultiesScreen

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
    FacultiesScreen(
        onDepartmentDepartmentSelected = event.onDepartmentInfoRequest,
        onExitRequest = onExitRequested
    )

}

