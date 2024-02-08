package com.just.cse.digital_diary.two_zero_two_three.root_home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.just.cse.digital_diary.features.admin_office.destination.AdminOfficeFeatureNavGraph
import com.just.cse.digital_diary.features.faculty.destination.FacultyFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.features.others.destination.graph.OtherFeatureNavGraph
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

    }

}
