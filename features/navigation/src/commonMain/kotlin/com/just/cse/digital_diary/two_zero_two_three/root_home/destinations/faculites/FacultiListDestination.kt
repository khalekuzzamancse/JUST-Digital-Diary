package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.faculites

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.destination.FacultyDestinationEvent
import com.just.cse.digital_diary.features.faculty.destination.FacultyModuleEntryPoint


@Composable
internal fun FacultyListDestination(
    event: FacultyDestinationEvent,
    onExitRequest: () -> Unit,
) {
    FacultyModuleEntryPoint(
        event = event,
        onExitRequest = onExitRequest
    )

}