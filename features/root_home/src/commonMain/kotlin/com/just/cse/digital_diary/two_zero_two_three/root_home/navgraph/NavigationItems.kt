package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.School
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem

val topMostDestinations = listOf(
    NavigationItem(
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        key = ""
    ),
    NavigationItem(
        label = "Faculty Members",
        unFocusedIcon = Icons.Outlined.School,
        key = ""
    ),
    NavigationItem(
        label = "Administrators",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,
        key = ""
    ),

    NavigationItem(
        label = "Message(Vice -Chancellor)",
        unFocusedIcon = Icons.Outlined.Message,
        key = ""
    ),
    NavigationItem(
        label = "About Us",
        unFocusedIcon = Icons.Outlined.Info,
        key = ""
    ),

    )
object RootDestinations{
    const val HOME=0
    const val FACULTY_MEMBERS=1
    const val ADMINISTRATION=2
    const val MESSAGE_FROM_VC=3
    const val ABOUT_US=4
}