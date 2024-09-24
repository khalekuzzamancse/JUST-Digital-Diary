package navigation

import academic.ui.admin.AddTeacherScreen
import academic.ui.public_.FacultyFeatureNavGraph
import academic.ui.public_.teachers.TeacherListEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import calendar.ui.ui.admin.AddAcademicCalenderScreen
import miscellaneous.OtherFeatureEvent
import miscellaneous.OtherFeatureNavGraph
import schedule.ui.ui.public_.ViewClassScheduleScreen
import schedule.ui.ui.public_.ViewExamScheduleScreen
import schedule.ui.ui.admin.add_class_schedule.AddClassScheduleScreen
import schedule.ui.ui.admin.add_exam_schedule.ExamScheduleAddScreen


@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    startDestination: String,
    isNavRailMode: Boolean,
    navController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        OtherFeatureNavGraph.graph(
            navGraphBuilder = this,
            onExitRequest = openDrawerRequest,
            onEvent = { event ->
                try {
                    when (event) {
                        is OtherFeatureEvent.CalenderRequest -> {
                            onEvent(AppEvent.WebVisitRequest(event.url))
                        }
                        is OtherFeatureEvent.NavigateToCalendarUpdate -> {
                            navController.navigate(GraphRoutes.CALENDAR_UPDATE)
                        }
                        is OtherFeatureEvent.NavigateToExamRoutineUpdate -> {
                            navController.navigate(GraphRoutes.EXAM_ROUTINE_UPDATE)
                        }
                        is OtherFeatureEvent.NavigateToClassRoutineUpdate -> {
                            navController.navigate(GraphRoutes.CLASS_ROUTINE_UPDATE)
                        }
                        is OtherFeatureEvent.NavigateToTeacherInfoUpdate -> {
                            navController.navigate(GraphRoutes.TEACHER_INFO_UPDATE)
                        }
                    }
                }
                catch (_:Exception){}


            }
        )
//        composable(GraphRoutes.ADMIN_OFFICE_FEATURE) {
//            AdminOfficeFeatureNavGraph.NavHost(
//                onEvent = { event ->
//                    toAppEvent(event)?.let(onEvent)
//                },
//                onMenuIconClick = openDrawerRequest
//            )
//        }
        composable(GraphRoutes.FACULTY_FEATURE) {
            FacultyFeatureNavGraph.NavHost(
                onEvent = { event ->
                    toAppEvent(event)?.let(onEvent)
                },
                onMenuIconClicked = openDrawerRequest,
                isNavRailMode = isNavRailMode
            )
        }
        composable(GraphRoutes.FACULTY_FEATURE) {
            FacultyFeatureNavGraph.NavHost(
                onEvent = { event ->
                    toAppEvent(event)?.let(onEvent)
                },
                onMenuIconClicked = openDrawerRequest,
                isNavRailMode = isNavRailMode
            )
        }
        composable(GraphRoutes.CLASS_SCHEDULE_VIEWER) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ViewClassScheduleScreen()
            }

        }
        composable(GraphRoutes.EXAM_SCHEDULE_VIEWER) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ViewExamScheduleScreen()
            }

        }
        composable(GraphRoutes.EXAM_SCHEDULE_VIEWER) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ViewExamScheduleScreen()
            }

        }
        composable(GraphRoutes.EXAM_ROUTINE_UPDATE){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                ExamScheduleAddScreen()
            }
        }
        composable(GraphRoutes.CLASS_ROUTINE_UPDATE){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AddClassScheduleScreen()
            }
        }
        composable(GraphRoutes.TEACHER_INFO_UPDATE){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AddTeacherScreen()
            }
        }
        composable(GraphRoutes.CALENDAR_UPDATE){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                AddAcademicCalenderScreen()
            }
        }


//        composable(GraphRoutes.NOTES_FEATURE) {
//            NotesFeatureNavGraph.Graph(
//                onExitRequest = openDrawerRequest,
//                onBackPressed = onBackPressed
//            )
//        }
//        composable(GraphRoutes.SEARCH) {
//            SearchFeatureNavGraph.Graph(
//                onEvent = { event ->
//                    if (event is SearchFeatureEvent.ExitRequest)
//                        openDrawerRequest()
//                    toAppEvent(event)?.let(onEvent)
//                },
//                onBackPress = onBackPressed
//            )
//        }


    }

}

private fun toAppEvent(event: TeacherListEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is TeacherListEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is TeacherListEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is TeacherListEvent.EmailRequest -> AppEvent.EmailRequest(event.email)

        else -> {
            null
        }
    }
    return ev

}

//private fun toAppEvent(event: AdminEmployeeListEvent): AppEvent? {
//    val ev: AppEvent? = when (event) {
//        is AdminEmployeeListEvent.CallRequest -> AppEvent.CallRequest(event.number)
//        is AdminEmployeeListEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
//        is AdminEmployeeListEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
//        else -> {
//            null
//        }
//    }
//    return ev
//}

//private fun toAppEvent(event: SearchFeatureEvent): AppEvent? {
//    val ev: AppEvent? = when (event) {
//        is SearchFeatureEvent.CallRequest -> AppEvent.CallRequest(event.number)
//        is SearchFeatureEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
//        is SearchFeatureEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
//        else -> null
//    }
//    return ev
//
//}

object GraphRoutes {
    const val FACULTY_FEATURE = "FacultyFeatureNavGraph.ROUTE"
    const val ADMIN_OFFICE_FEATURE = "AdminOfficeFeatureNavGraph.ROUTE"
    const val TOPMOST_OTHER_FEATURE = OtherFeatureNavGraph.ROUTE
    const val NOTES_FEATURE = "NotesFeatureNavGraph.ROUTE"
    const val SEARCH = "Search"
    const val CLASS_SCHEDULE_VIEWER = "CLASS_SCHEDULE"
    const val EXAM_SCHEDULE_VIEWER = "EXAM_SCHEDULE"
    // Admin-specific routes
    const val CALENDAR_UPDATE = "CalendarUpdateFeatureNavGraph.ROUTE"
    const val TEACHER_INFO_UPDATE = "TeacherInfoUpdateFeatureNavGraph.ROUTE"
    const val CLASS_ROUTINE_UPDATE = "ClassRoutineUpdateFeatureNavGraph.ROUTE"
    const val EXAM_ROUTINE_UPDATE = "ExamRoutineUpdateFeatureNavGraph.ROUTE"
}
