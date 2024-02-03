package com.just.cse.digital_diary.features.faculty.destination

data class FacultyDestinationEvent(
    val onExitRequested: () -> Unit={},
    val onDepartmentInfoRequest: (String)->Unit,
)