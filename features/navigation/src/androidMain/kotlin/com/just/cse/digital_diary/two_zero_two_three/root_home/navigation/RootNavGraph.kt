package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.just.cse.digital_diary.features.admin_office.destination.AdminOfficeFeatureNavGraph
import com.just.cse.digital_diary.features.faculty.destination.FacultyFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.navgraph.AuthenticationNavGraph
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.notes.navgraph.graph.NotesFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    appEvent: AppEvent,
    openDrawerRequest: () -> Unit,
    navController: NavHostController,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = GraphRoutes.OTHER_FEATURE
    ) {

        FacultyFeatureNavGraph.graph(this, navController)
        AdminOfficeFeatureNavGraph.graph(this, navController)
        OtherFeatureNavGraph.graph(navGraphBuilder = this, onExitRequest = openDrawerRequest)
        composable(GraphRoutes.NOTES_FEATURE){
            NotesFeatureNavGraph.Graph()
        }
        composable(GraphRoutes.AUTH){
            AuthenticationNavGraph.Graph()
        }

    }

}
object GraphRoutes{
    const val FACULTY_FEATURE= FacultyFeatureNavGraph.ROUTE
    const val ADMIN_OFFICE_FEATURE= AdminOfficeFeatureNavGraph.ROUTE
    const val OTHER_FEATURE= OtherFeatureNavGraph.ROUTE
    const val NOTES_FEATURE= NotesFeatureNavGraph.ROUTE
    const val AUTH=AuthenticationNavGraph.ROUTE
}
