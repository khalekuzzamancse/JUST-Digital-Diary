package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.outlined.Science
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.BottomSheetNavigationSection
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.NavigationItem
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Department

@Composable
internal fun AnimatedBottomSheet(
    visible: Boolean,
    selectedIndex: Int,
    faculties: List<Department>,
    onFacultyClick: (Int) -> Unit,
) {
    val destinations = faculties.map {
        NavigationItem(
            label = it.fullName,
            unFocusedIcon = Icons.Outlined.Science,
            focusedIcon =Icons.Filled.Science,
            key = it.id
        )
    }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioLowBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
    ) {
        BottomSheetNavigationSection(
            destinations = destinations,
            onItemClick=onFacultyClick,
            currentDestinationIndex = selectedIndex

        )
    }

}

