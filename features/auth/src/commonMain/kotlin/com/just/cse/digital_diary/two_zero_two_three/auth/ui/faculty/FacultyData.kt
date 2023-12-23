package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.ArtTrack
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Flaky
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.OtherHouses
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.ArtTrack
import androidx.compose.material.icons.outlined.Engineering
import androidx.compose.material.icons.outlined.Flaky
import androidx.compose.material.icons.outlined.HealthAndSafety
import androidx.compose.material.icons.outlined.MedicalServices
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.Psychology
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Science
import androidx.compose.material.icons.rounded.Flaky
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem


val faculties = listOf(
    NavigationItem(
        label = "Faculty of Engineering and Technology",
        unFocusedIcon = Icons.Outlined.Engineering,
        focusedIcon = Icons.Filled.Engineering,
    ),
    NavigationItem(
        label = "Faculty of Biological Science and Technology",
        unFocusedIcon = Icons.Outlined.MedicalServices,
        focusedIcon = Icons.Filled.MedicalServices,
    ),
    NavigationItem(
        label = "Faculty of Applied Science and Technology",
        unFocusedIcon = Icons.Outlined.Science,
        focusedIcon = Icons.Filled.Science,
    ),
    NavigationItem(
        label = "Faculty of Health Science",
        unFocusedIcon = Icons.Outlined.HealthAndSafety,
        focusedIcon = Icons.Filled.HealthAndSafety,
    ),
    NavigationItem(
        label = "Faculty of Arts and Social Science",
        unFocusedIcon =Icons.Outlined.ArtTrack,
        focusedIcon = Icons.Filled.ArtTrack,
    ),
    NavigationItem(
        label = "Faculty of Science",
        unFocusedIcon = Icons.Outlined.Science,
        focusedIcon = Icons.Filled.Science,
    ),
    NavigationItem(
        label = "Faculty of Business Studies",
        unFocusedIcon = Icons.Outlined.Psychology,
        focusedIcon = Icons.Filled.Psychology,
    )
)
val administrativeOffices = listOf(
    NavigationItem(
        label = "Offices",
        unFocusedIcon = Icons.Outlined.AccountBalance,
        focusedIcon = Icons.Filled.AccountBalance,
    ),
    NavigationItem(
        label = "Provost Offices",
        unFocusedIcon = Icons.Outlined.AdminPanelSettings,
        focusedIcon = Icons.Filled.AdminPanelSettings,
    ),
    NavigationItem(
        label = "Research Laboratory",
        unFocusedIcon = Icons.Outlined.Flaky,
        focusedIcon = Icons.Filled.Flaky,
    ),
    NavigationItem(
        label = "Institution  and Cell",
        unFocusedIcon = Icons.Outlined.School,
        focusedIcon = Icons.Filled.School,
    ),
    NavigationItem(
        label = "Other",
        unFocusedIcon =Icons.Outlined.OtherHouses,
        focusedIcon = Icons.Filled.OtherHouses,
    ),
)