package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.OfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.faculty_list.state.FacultyListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.event.AdminOfficesDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar


@Composable
internal fun CompactModeLayout(
    facultyListState: FacultyListState,
    onEvent: (AdminOfficesDestinationEvent) -> Unit,
) {
    /*
  Note recomposition the faculty destination because recomposition causes animation
                as,we have animation when showing department destination as a result ,to give better user
                            experience not hiding(recompose) the faculties,
                 Use a department background that hide the faculties  without recomposing the faculties
                  */

    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Admin Office",
                onNavigationIconClick = {}
            )
        },
    ) {
        OfficeList(
            modifier = Modifier.padding(it),
            onEvent = onEvent,
            state = facultyListState
        )
    }

}

