package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just.cse.digital_diary.features.admin_office.destination.AdminOfficeFeatureNavGraph
import com.just.cse.digital_diary.features.admin_office.destination.event.AdminEvent
import com.just.cse.digital_diary.features.faculty.destination.FacultyFeatureNavGraph
import com.just.cse.digital_diary.features.faculty.destination.event.FacultyFeatureEvent
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph.AuthenticationNavGraph
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.graph.NotesFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent
import com.just.cse.digital_diary.two_zero_two_three.search.navgraph.graph.SearchFeatureEvent
import com.just.cse.digital_diary.two_zero_two_three.search.navgraph.graph.SearchFeatureNavGraph

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    onLoginSuccess: (String, String) -> Unit,
    navController: NavHostController,
) {
    LaunchedEffect(Unit) {
        navController.currentBackStack.collect {
            println("RootNavGraph:Stack: ${it.map { it.destination.route }}")
        }
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GraphRoutes.OTHER_FEATURE
    ) {
        OtherFeatureNavGraph.graph(navGraphBuilder = this, onExitRequest = openDrawerRequest)
        composable(GraphRoutes.ADMIN_OFFICE_FEATURE) {
            AdminOfficeFeatureNavGraph.Graph(
                onEvent = { event ->
                    if (event is AdminEvent.ExitRequest)
                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                },
                onBackPress = onBackPressed
            )
        }
        composable(GraphRoutes.FACULTY_FEATURE) {
            FacultyFeatureNavGraph.Graph(
                onEvent = { event ->
                    if (event is FacultyFeatureEvent.ExitRequest)
                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                },
                onBackPressed = onBackPressed
            )
        }
        composable(GraphRoutes.NOTES_FEATURE) {
            NotesFeatureNavGraph.Graph(
                onExitRequest = openDrawerRequest,
                onBackPressed = onBackPressed
            )
        }
        composable(GraphRoutes.SEARCH) {
            SearchFeatureNavGraph.Graph(
                onEvent = { event ->
                    if (event is SearchFeatureEvent.ExitRequest)
                        openDrawerRequest()
                    toAppEvent(event)?.let(onEvent)
                }
            )
        }
        composable(GraphRoutes.AUTH) {
            AuthenticationNavGraph.Graph(onLoginSuccess = onLoginSuccess)
        }

    }

}

private fun toAppEvent(event: FacultyFeatureEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is FacultyFeatureEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is FacultyFeatureEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is FacultyFeatureEvent.EmailRequest -> AppEvent.EmailRequest(event.email)

        else -> {
            null
        }
    }
    return ev

}

private fun toAppEvent(event: AdminEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is AdminEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is AdminEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is AdminEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
        else -> {
            null
        }
    }
    return ev
}

private fun toAppEvent(event: SearchFeatureEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is SearchFeatureEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is SearchFeatureEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is SearchFeatureEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
        else -> null
    }
    return ev

}

object GraphRoutes {
    const val FACULTY_FEATURE = "FacultyFeatureNavGraph.ROUTE"
    const val ADMIN_OFFICE_FEATURE = "AdminOfficeFeatureNavGraph.ROUTE"
    const val OTHER_FEATURE = OtherFeatureNavGraph.ROUTE
    const val NOTES_FEATURE = "NotesFeatureNavGraph.ROUTE"
    const val AUTH = "AuthenticationNavGraph.ROUTE"
    const val SEARCH = "Search"
}
