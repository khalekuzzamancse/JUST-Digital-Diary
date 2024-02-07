package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.component.faculty_list.state.FacultyListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps


/**
 * * It Show the have the List of in Bottom sheet.
 *  * In Compact Window Faculties will be shown in the bottom sheet,in NonCompact Window Faculties will be shown in SIDE_SHEET
 * @param modifier [Modifier]
 * @param destinations list of [NavigationItemInfo2] to represent the faculties
 * @param onDestinationSelected called when a faculty is selected
 * @param selectedDestinationIndex the destination that is selected.it is used to highlight the selected faculty as [NavigationItemInfo2]
 */

@Composable
fun FacultyList(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
) {
    val destinations = state.faculties.map { faculty ->
        NavigationItemInfo2(
            label = faculty.name,
            iconText = faculty.numberOfDepartment,
            key = faculty.id
        )
    }

        VerticalListNavigation(
            modifier = modifier.fillMaxWidth(),
            destinations = destinations,
            onDestinationSelected = { index ->
                onEvent(FacultyListEvent.FacultySelected(index))
            },
            selectedDestinationIndex = state.selected?:-1
        )

}

