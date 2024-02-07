package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.admin_office.destination.nav_graph.AdminOfficeNavGraph
import com.just.cse.digital_diary.features.faculty.destination.nav_graph.FacultyFeatureNavGraph
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations

object NavigationGraph {


    @Composable
    fun NavHost(
        destination: Int,
        onTeacherListRequest:(String) -> Unit,
    ) {

        when (destination) {
            RootDestinations.HOME -> {
                TopMostDestinations.Home(
                    onCreateNoteRequest = {},
                )
            }

            RootDestinations.FACULTY_LIST -> {
                FacultyFeatureNavGraph()

            }

            RootDestinations.ADMIN_OFFICE -> {
                AdminOfficeNavGraph(
                    onExitRequest ={}
                )
            }
            RootDestinations.MESSAGE_FROM_VC -> {
                TopMostDestinations.MessageFromVC { }
            }

            RootDestinations.ABOUT_US -> {
                TopMostDestinations.AboutUs { }
            }

            RootDestinations.Search -> {}
            RootDestinations.NOTE_LIST -> {}
            RootDestinations.EventGallery -> {
                TopMostDestinations.EventGallery { }
            }

            RootDestinations.EXPLORE_JUST -> {}
        }

    }

}