package com.just.cse.digital_diary.features.faculty.components.event

data class FacultyModuleEvent(
    val onExitRequested: () -> Unit={},
    val onDepartmentInfoRequest: (String)->Unit,
)