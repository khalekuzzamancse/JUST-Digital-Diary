package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.FacultyModuleEntryPoint
import com.just.cse.digital_diary.features.faculty.faculty.event.FacultyModuleEvent


@Composable
internal fun FacultyListDestination(
    event: FacultyModuleEvent,
    onExitRequest: () -> Unit,
) {
    FacultyModuleEntryPoint(
        event = event,
        onExitRequest = onExitRequest
    )

}