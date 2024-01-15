package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notes
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
    NavigationItem(
        label = "Edit Profile",
        unFocusedIcon = Icons.Outlined.Edit,
        focusedIcon = Icons.Filled.Edit,
        key = ""
    ),
    NavigationItem(
        label = "Notes",
        unFocusedIcon = Icons.Outlined.Notes,
        focusedIcon = Icons.Filled.Notes,
        key = ""
    ),

    )

object RootDestinations {
    const val HOME = 0
    const val FACULTY_MEMBERS = 1
    const val ADMINISTRATION = 2
    const val MESSAGE_FROM_VC = 3
    const val ABOUT_US = 4
    const val  EditProfile=5
    const val Notes=6
}