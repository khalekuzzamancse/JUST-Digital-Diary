package com.just.cse.digital_diary.features.faculty.destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.faculty_list.FacultiesScreen
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

/**
 * * This is Only the entry point to the Faculty module
 * * it will delegate to the NavGraph([NavGraph]) of this module.
 * @param onExitRequest :to exit from the Module
 *
 */
@Composable
fun FacultyModuleEntryPoint(
    event: FacultyDestinationEvent,
    onExitRequest: () -> Unit={},
) {

    FacultiesScreen(
        facultyListRepository = FacultyComponentProvider.getFacultyRepository(),
        departmentListRepository = FacultyComponentProvider.getDepartListRepository(),
        onEmployeeListRequest = event.onDepartmentInfoRequest
    )

}

