@file:Suppress("UnUsed")
package profile.presentationlogic

sealed interface ProfileEvent {
    data object CalendarUpdate : ProfileEvent
    data object ExamRoutineUpdate : ProfileEvent
    data object ClassRoutineUpdate : ProfileEvent
    data object InsertTeacherRequest:ProfileEvent
    data object InsertDepartmentRequest:ProfileEvent
    data object InsertFacultyRequest:ProfileEvent
}