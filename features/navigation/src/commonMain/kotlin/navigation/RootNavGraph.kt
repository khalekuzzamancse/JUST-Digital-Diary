package navigation

import academic.ui.other.FacultyFeatureNavGraph
import administration.ui.officers.employeelist.components.AdminEmployeeListEvent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import faculty.ui.teachers.component.TeacherListEvent
import miscellaneous.OtherFeatureEvent
import miscellaneous.OtherFeatureNavGraph


@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    startDestination:String,
    isNavRailMode:Boolean,
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
            onEvent = {event->
                when(event){
                    is OtherFeatureEvent.CalenderRequest->{
                        onEvent(AppEvent.WebVisitRequest(event.url))
                    }
                }
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
                onMenuIconClicked =openDrawerRequest,
                isNavRailMode = isNavRailMode
            )
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

private fun toAppEvent(event: AdminEmployeeListEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is AdminEmployeeListEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is AdminEmployeeListEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is AdminEmployeeListEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
        else -> {
            null
        }
    }
    return ev
}

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
}
