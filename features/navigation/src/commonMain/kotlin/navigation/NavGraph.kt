package navigation

import academic.ui.AcademicModuleEvent
import academic.ui.admin.AddTeacherScreen
import academic.ui.public_.AcademicRoute
import administration.ui.public_.AdministrationRoute
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import calendar.ui.ui.admin.AddAcademicCalenderScreen
import miscellaneous.MiscFeatureEvent
import miscellaneous.ui.AboutUsRoute
import miscellaneous.ui.eventGallery.EventsRoute
import miscellaneous.ui.home.HomeRoute
import miscellaneous.ui.vcmessage.MessageFromVCRoute
import schedule.ui.ui.admin.add_class_schedule.AddClassScheduleScreen
import schedule.ui.ui.admin.add_exam_schedule.ExamScheduleAddScreen
import schedule.ui.ui.public_.ViewClassScheduleScreen
import schedule.ui.ui.public_.ViewExamScheduleScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    startDestination: String,
    isNavRailMode: Boolean,
    navController: NavHostController,
    onMiscFeatureEvent: (MiscFeatureEvent) -> Unit,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(GraphRoutes.HOME) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                HomeRoute(
                    onEvent = onMiscFeatureEvent
                )
            }

        }

        composable(GraphRoutes.ACADEMIC_FEATURES) {
            AcademicRoute(
                onEvent = { event ->
                    toAppEvent(event)?.let(onEvent)
                },
                navigationIcon = if (!isNavRailMode) {
                    {
                        IconButton(onClick = openDrawerRequest) {
                            Icon(Icons.Filled.Menu, contentDescription = "back")
                        }
                    }
                } else null,
                token = NavigationFactory.token.value
            )
        }

        composable(GraphRoutes.ADMIN_OFFICE_FEATURE) {
            AdministrationRoute(
                token = NavigationFactory.token.value,
                onEvent = {},
                navigationIcon = if (!isNavRailMode) {
                    {
                        IconButton(onClick = openDrawerRequest) {
                            Icon(Icons.Filled.Menu, contentDescription = "back")
                        }
                    }
                } else null
            )
        }
        composable(GraphRoutes.CLASS_SCHEDULE_VIEWER) {
            _DrawerIconDecorator(
                isNavRailMode = isNavRailMode,
                onMenuIconClick = openDrawerRequest,
            ) {
                ViewClassScheduleScreen()
            }

        }
        composable(GraphRoutes.EXAM_SCHEDULE_VIEWER) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                ViewExamScheduleScreen()
            }
        }

        composable(GraphRoutes.EXAM_ROUTINE_UPDATE) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                ExamScheduleAddScreen()
            }
        }
        composable(GraphRoutes.CLASS_ROUTINE_UPDATE) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AddClassScheduleScreen()
            }
        }
        composable(GraphRoutes.TEACHER_INFO_UPDATE) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AddTeacherScreen()

            }
        }
        composable(GraphRoutes.CALENDAR_UPDATE) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AddAcademicCalenderScreen()
            }

        }
        composable(GraphRoutes.VC_MESSAGES) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                MessageFromVCRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(GraphRoutes.EVENT_GALLERY) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                EventsRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(GraphRoutes.ABOUT_US) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AboutUsRoute()
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

private fun toAppEvent(event: AcademicModuleEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is AcademicModuleEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is AcademicModuleEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is AcademicModuleEvent.EmailRequest -> AppEvent.EmailRequest(event.email)

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
    const val ACADEMIC_FEATURES = "FacultyFeatureNavGraph.ROUTE"
    const val ADMIN_OFFICE_FEATURE = "AdminOfficeFeatureNavGraph.ROUTE"
    const val HOME = "HOME_ROUTE"
    const val VC_MESSAGES = "VC_MESSAGES_ROUTE"
    const val ABOUT_US = "About us route"
    const val NOTES_FEATURE = "NotesFeatureNavGraph.ROUTE"
    const val SEARCH = "Search"
    const val CLASS_SCHEDULE_VIEWER = "CLASS_SCHEDULE"
    const val EXAM_SCHEDULE_VIEWER = "EXAM_SCHEDULE"
    const val EVENT_GALLERY = "Event Gallery"

    // Admin-specific routes
    const val CALENDAR_UPDATE = "CalendarUpdateFeatureNavGraph.ROUTE"
    const val TEACHER_INFO_UPDATE = "TeacherInfoUpdateFeatureNavGraph.ROUTE"
    const val CLASS_ROUTINE_UPDATE = "ClassRoutineUpdateFeatureNavGraph.ROUTE"
    const val EXAM_ROUTINE_UPDATE = "ExamRoutineUpdateFeatureNavGraph.ROUTE"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun _DrawerIconDecorator(
    isNavRailMode: Boolean,
    onMenuIconClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (isNavRailMode) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    } else {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(
                            onClick = onMenuIconClick
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Drawer Icon")
                        }
                    }
                )
            }
        ) {
            Box(
                modifier = Modifier.padding(it).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }

    }
}