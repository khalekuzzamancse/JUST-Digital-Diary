package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.ArtTrack
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Flaky
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Science
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem


val faculties = listOf(
    NavigationItem(
        label = "Faculty of Engineering and Technology",
        unFocusedIcon = Icons.Outlined.Engineering,
    ),
    NavigationItem(
        label = "Faculty of Biological Science and Technology",
        unFocusedIcon = Icons.Outlined.MedicalServices,
    ),
    NavigationItem(
        label = "Faculty of Applied Science and Technology",
        unFocusedIcon = Icons.Outlined.Science,
    ),
    NavigationItem(
        label = "Faculty of Health Science",
        unFocusedIcon = Icons.Outlined.HealthAndSafety,
    ),
    NavigationItem(
        label = "Faculty of Arts and Social Science",
        unFocusedIcon =Icons.Outlined.ArtTrack,
    ),
    NavigationItem(
        label = "Faculty of Science",
        unFocusedIcon = Icons.Outlined.Science,
    ),
    NavigationItem(
        label = "Faculty of Business Studies",
        unFocusedIcon = Icons.Outlined.Psychology,
    )
)
val administrativeOffices = listOf(
    NavigationItem(
        label = "Offices",
        unFocusedIcon = Icons.Outlined.AccountBalance,
    ),
    NavigationItem(
        label = "Provost Offices",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,
    ),
    NavigationItem(
        label = "Research Laboratory",
        unFocusedIcon = Icons.Outlined.Flaky,
    ),
    NavigationItem(
        label = "Institution  and Cell",
        unFocusedIcon = Icons.Outlined.School,
    ),
    NavigationItem(
        label = "Other",
        unFocusedIcon =Icons.Outlined.OtherHouses,
    ),
)