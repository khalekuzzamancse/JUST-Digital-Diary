package navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import common.ui.Destination
import miscellaneous.MiscFeatureEvent
import navigation.component.NavDestination

class Navigator(
    private val navController: NavHostController,
    private val onEvent: (AppEvent) -> Unit,
) {


    internal fun navigator(destination: Destination) {
        when (destination) {
            NavDestination.Home -> {
                navigateAsTopMostDestination(GraphRoutes.HOME, navController)
            }

            NavDestination.FacultyList -> {
                navigateAsTopMostDestination(GraphRoutes.ACADEMIC_FEATURES, navController)
            }

            NavDestination.AdminOffice -> {
                navController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
            }

            NavDestination.Search -> {
                navigateAsTopMostDestination(GraphRoutes.SEARCH, navController)
            }

            NavDestination.NoteBook -> {
                navigateAsTopMostDestination(GraphRoutes.NOTES_FEATURE, navController)
            }

            NavDestination.ClassSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.CLASS_SCHEDULE_VIEWER, navController)
            }

            NavDestination.ExamSchedule -> {
                navigateAsTopMostDestination(GraphRoutes.EXAM_SCHEDULE_VIEWER, navController)
            }

            NavDestination.ExploreJust -> {
                onEvent(AppEvent.WebVisitRequest("https://just.edu.bd/"))
            }

            NavDestination.AboutUs -> {
                navigateAsTopMostDestination(GraphRoutes.ABOUT_US, navController)
            }

            NavDestination.EventGallery -> {
                navigateAsTopMostDestination(GraphRoutes.EVENT_GALLERY, navController)
            }

            NavDestination.MessageFromVC -> {
                navigateAsTopMostDestination(GraphRoutes.VC_MESSAGES, navController)
            }

            else -> {}
        }
    }
    fun onMiscFeatureEvent(event:MiscFeatureEvent){
        try {
            when (event) {
                is MiscFeatureEvent.CalenderRequest -> {
                    onEvent(AppEvent.WebVisitRequest(event.url))
                }

                is MiscFeatureEvent.NavigateToFacultyList -> {
                    navController.navigate(GraphRoutes.ACADEMIC_FEATURES)
                }

                is MiscFeatureEvent.NavigateTAdminOfficeList -> {
                    navController.navigate(GraphRoutes.ADMIN_OFFICE_FEATURE)
                }

                is MiscFeatureEvent.NavigateToCalendarUpdate -> {
                    navController.navigate(GraphRoutes.CALENDAR_UPDATE)
                }


                is MiscFeatureEvent.NavigateToExamRoutineUpdate -> {
                    navController.navigate(GraphRoutes.EXAM_ROUTINE_UPDATE)
                }

                is MiscFeatureEvent.NavigateToClassRoutineUpdate -> {
                    navController.navigate(GraphRoutes.CLASS_ROUTINE_UPDATE)
                }

                is MiscFeatureEvent.NavigateToTeacherInfoUpdate -> {
                    navController.navigate(GraphRoutes.TEACHER_INFO_UPDATE)
                }


            }
        } catch (_: Exception) {
        }

    }



    fun pop() {
        navController.popBackStack()
    }

    private fun navigateAsTopMostDestination(
        destination: String,
        navController: NavHostController
    ) {
        navController.navigate(destination) {
            navController.graph.findStartDestination().route?.let {
                popUpTo(it) {
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}
