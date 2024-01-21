package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.child_destination

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEntryPoint
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEvent

@Composable
internal fun FacultyListDestination(
    event: FacultyModuleEvent,
    onExitRequested: () -> Unit,
) {
    FacultyModuleEntryPoint(
        event = event,
        onExitRequested = onExitRequested
    )

}