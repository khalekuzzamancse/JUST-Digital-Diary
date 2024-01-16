package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.BottomSheetNavigationSection2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Department

@Composable
internal fun AnimatedBottomSheet(
    visible: Boolean,
    selectedIndex: Int,
    faculties: List<Department>,
    onFacultyClick: (Int) -> Unit,
) {
    val destinations = faculties.map {
        NavigationItemInfo2(
            label = it.fullName,
            iconText = it.shortName,
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
        BottomSheetNavigationSection2(
            destinations = destinations,
            onItemClick=onFacultyClick,
            currentDestinationIndex = selectedIndex

        )
    }

}

