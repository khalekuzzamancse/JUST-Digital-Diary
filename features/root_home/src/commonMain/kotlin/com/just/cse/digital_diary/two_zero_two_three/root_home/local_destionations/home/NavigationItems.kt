package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Search
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo

val topMostDestinations = listOf(
    NavigationItemInfo(
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        key = ""
    ),

    NavigationItemInfo(
        label = "Faculty Members",
        unFocusedIcon = Icons.Outlined.School,
        key = ""
    ),
    NavigationItemInfo(
        label = "Administrators",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,
        key = ""
    ),

    NavigationItemInfo(
        label = "Message(Vice -Chancellor)",
        unFocusedIcon = Icons.Outlined.Message,
        key = ""
    ),
    NavigationItemInfo(
        label = "About Us",
        unFocusedIcon = Icons.Outlined.Info,
        key = ""
    ),
    NavigationItemInfo(
        label = "Search",
        unFocusedIcon = Icons.Outlined.Search,
        focusedIcon = Icons.Filled.Search,
        key = ""
    ),
    NavigationItemInfo(
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
    const val  Search=5
    const val Notes=6
}