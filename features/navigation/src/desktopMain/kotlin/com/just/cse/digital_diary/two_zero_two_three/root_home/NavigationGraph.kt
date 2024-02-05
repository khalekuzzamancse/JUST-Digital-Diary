package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.TopMostDestinations

object NavigationGraph {
    @Composable
    fun NavHost(destination: Int) {
        when (destination) {
            RootDestinations.HOME -> {
                TopMostDestinations.Home(
                    onCreateNoteRequest = {},
                )
            }

            RootDestinations.FACULTY_LIST -> {
                TopMostDestinations.FacultyList(
                    onDepartmentInfoRequest = {},
                    onExitRequest = {}
                )

            }

            RootDestinations.ADMIN_OFFICE -> {}
            RootDestinations.MESSAGE_FROM_VC -> {
                TopMostDestinations.MessageFromVC {  }
            }

            RootDestinations.ABOUT_US -> {
                TopMostDestinations.AboutUs {  }
            }
            RootDestinations.Search -> {}
            RootDestinations.NOTE_LIST -> {}
            RootDestinations.EventGallery -> {
                TopMostDestinations.EventGallery {  }
            }

            RootDestinations.EXPLORE_JUST -> {}
        }

    }

}