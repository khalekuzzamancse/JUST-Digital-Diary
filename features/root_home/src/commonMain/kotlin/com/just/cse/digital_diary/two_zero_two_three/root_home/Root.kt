package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthModuleEntryPoint
import com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.local_destionations.search.SearchDestination
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.local_destination_graph.RootDestinationScreen
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
fun RootModule(appEvent: AppEvent) {
    AppTheme {
        RootDestinationScreen(appEvent = appEvent)

  }


}
//  Navigator()
//  RootNavGraph(event =event)
//        var loginSuccess by remember {
//            mutableStateOf(false)
//        }
//        if (loginSuccess) {
//            RootNavGraph(event =event)
//        } else {

//        }




