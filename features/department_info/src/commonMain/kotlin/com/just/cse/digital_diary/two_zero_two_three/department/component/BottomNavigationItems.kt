package com.just.cse.digital_diary.two_zero_two_three.department.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupervisorAccount
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo


internal val bottomNavigationItems = listOf(
    NavigationItemInfo(
        key = 0,
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        focusedIcon = Icons.Filled.Home,

        ),
    NavigationItemInfo(
        key = 1,
        label = "Teachers",
        unFocusedIcon = Icons.Outlined.SupervisorAccount,
        focusedIcon = Icons.Filled.SupervisorAccount,
    ),


    )
