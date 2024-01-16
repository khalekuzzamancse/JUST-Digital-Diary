package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material.icons.outlined.Work
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.NavigationItem


internal val departmentSubDestinations = listOf(
    NavigationItem(
        key = 0,
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        focusedIcon = Icons.Filled.Home,

        ),
    NavigationItem(
        key = 1,
        label = "Teachers",
        unFocusedIcon = Icons.Outlined.SupervisorAccount,
        focusedIcon = Icons.Filled.SupervisorAccount,
        badge = "20"
    ),
    NavigationItem(
        key = 2,
        label = "Staffs",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work,
        badge = "16"
    ),

    )
