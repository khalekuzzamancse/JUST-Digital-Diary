package com.just.cse.digital_diary.features.faculty.faculty

data class FacultyModuleEvent(
    val onExitRequested: () -> Unit={},
    val onDepartmentInfoRequest: (String)->Unit={},
)