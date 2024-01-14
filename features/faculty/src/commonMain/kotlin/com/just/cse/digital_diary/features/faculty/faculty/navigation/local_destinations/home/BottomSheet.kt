package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetItemDefaultDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetNavigation
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyInfo

@Composable
fun BottomSheet(
    visible:Boolean,
    currentDestinationIndex:Int,
    faculties: List<FacultyInfo>,
    onItemClick: (Int) -> Unit,
) {
    val destinations = faculties.map {
        NavigationItem(
            label = it.name,
            unFocusedIcon = it.logo,
            key = it.id
        )
    }
    AnimatedVisibility(
        visible = visible
    ) {
        BottomSheetNavigation(
            visibilityDelay = 0,
            destinations = destinations,
            destinationDecorator = { index ->
                BottomSheetItemDefaultDecorator(
                    selected = currentDestinationIndex == index,
                    onClick = {
                        onItemClick(index)
                    },
                    navigationItem = destinations[index]
                )
            }
        )
    }

}