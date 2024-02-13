package com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.automirrored.outlined.Message
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Message
import androidx.compose.material.icons.outlined.Notes
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Search
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.DrawerNavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo

internal val rootModuleTopMostDestinations = listOf(
    DrawerNavigationItem(
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,

    ),

    DrawerNavigationItem(
        label = "Faculty List",
        unFocusedIcon = Icons.Outlined.School,

    ),
    DrawerNavigationItem(
        label = "Admin Offices",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,

    ),

    DrawerNavigationItem(
        label = "Message(Vice -Chancellor)",
        unFocusedIcon = Icons.AutoMirrored.Outlined.Message,

    ),

    DrawerNavigationItem(
        label = "Search",
        unFocusedIcon = Icons.Outlined.Search,
        focusedIcon = Icons.Filled.Search,

    ),

    DrawerNavigationItem(
        label = "Event Gallery",
        unFocusedIcon = Icons.Outlined.Image,
        focusedIcon = Icons.Filled.Image,

    ),
    DrawerNavigationItem(
        label = "Explore JUST",
        unFocusedIcon = Icons.Outlined.Explore,
        focusedIcon = Icons.Filled.Explore,
    ),
    DrawerNavigationItem(
        label = "Notes",
        unFocusedIcon = Icons.AutoMirrored.Outlined.Notes,
        focusedIcon = Icons.AutoMirrored.Filled.Notes,

        ),
    DrawerNavigationItem(
        label = "About Us",
        unFocusedIcon = Icons.Outlined.Info,

        ),
)

internal object RootDestinations {
    const val HOME = 0
    const val FACULTY_LIST = 1
    const val ADMIN_OFFICE = 2
    const val MESSAGE_FROM_VC = 3
    const val Search = 4
    const val EventGallery = 5
    const val EXPLORE_JUST = 6
    const val NOTE_LIST = 7
    const val ABOUT_US = 8
}

