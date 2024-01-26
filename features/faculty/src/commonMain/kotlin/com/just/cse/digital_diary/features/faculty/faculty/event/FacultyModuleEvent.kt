package com.just.cse.digital_diary.features.faculty.faculty.event

data class FacultyModuleEvent(
    val onExitRequested: () -> Unit={},
    val onDepartmentInfoRequest: (String)->Unit,
)