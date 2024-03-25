package navigation

import administration.navgraph.AdminOfficeFeatureNavGraph
import administration.ui.officers.employeelist.components.AdminEmployeeListEvent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import queryservice.ui.navgraph.SearchFeatureEvent
import queryservice.ui.navgraph.SearchFeatureNavGraph
import faculty.route.FacultyFeatureNavGraph
import faculty.ui.teachers.teacherlist.component.TeacherListEvent
import miscellaneous.ui.OtherFeatureEvent
import miscellaneous.ui.OtherFeatureNavGraph
import notebook.ui.navigation.NotesFeatureNavGraph

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    startDestination:String,
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
        composable(GraphRoutes.ADMIN_OFFICE_FEATURE) {
            AdminOfficeFeatureNavGraph.NavHost(
                onEvent = { event ->
//                    if (event is AdminEvent.ExitRequest)
//                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                },
                onExitRequest = onBackPressed
            )
        }
        composable(GraphRoutes.FACULTY_FEATURE) {
            FacultyFeatureNavGraph.NavHost(
                onEvent = { event ->
//                    if (event is FacultyFeatureEvent.ExitRequest)
                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                },
                onExitRequest = onBackPressed
            )
        }
        composable(GraphRoutes.NOTES_FEATURE) {
            NotesFeatureNavGraph.Graph(
                onExitRequest = openDrawerRequest,
                onBackPressed = onBackPressed
            )
        }
        composable(GraphRoutes.SEARCH) {
            queryservice.ui.navgraph.SearchFeatureNavGraph.Graph(
                onEvent = { event ->
                    if (event is queryservice.ui.navgraph.SearchFeatureEvent.ExitRequest)
                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                },
                onBackPress = onBackPressed
            )
        }


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

private fun toAppEvent(event: queryservice.ui.navgraph.SearchFeatureEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is queryservice.ui.navgraph.SearchFeatureEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is queryservice.ui.navgraph.SearchFeatureEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is queryservice.ui.navgraph.SearchFeatureEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
        else -> null
    }
    return ev

}

object GraphRoutes {
    const val FACULTY_FEATURE = "FacultyFeatureNavGraph.ROUTE"
    const val ADMIN_OFFICE_FEATURE = "AdminOfficeFeatureNavGraph.ROUTE"
    const val TOPMOST_OTHER_FEATURE = OtherFeatureNavGraph.ROUTE
    const val NOTES_FEATURE = "NotesFeatureNavGraph.ROUTE"
    const val SEARCH = "Search"
}
