@file:Suppress("UnUsed")
package profile.presentationlogic

sealed interface ProfileEvent {
    data object NavigateToCalendarUpdate : ProfileEvent
    data object NavigateToExamRoutineUpdate : ProfileEvent
    data object NavigateToClassRoutineUpdate : ProfileEvent
    data object NavigateToTeacherInfoUpdate : ProfileEvent
}