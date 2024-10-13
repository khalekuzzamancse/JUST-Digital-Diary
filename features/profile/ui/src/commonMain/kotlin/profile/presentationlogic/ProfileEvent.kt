@file:Suppress("UnUsed")
package profile.presentationlogic

sealed interface ProfileEvent {
    data object NavigateToCalendarUpdate : ProfileEvent
    data object NavigateToExamRoutineUpdate : ProfileEvent
    data object NavigateToClassRoutineUpdate : ProfileEvent
    data object NavigateToTeacherInfoUpdate : ProfileEvent
    data object TeacherInsertRequest:ProfileEvent
    data object DepartmentInsertRequest:ProfileEvent
    data object FacultyInsertRequest:ProfileEvent
}