package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.destination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.faculty_list.FacultyList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.faculty_list.state.FacultyListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.component.home.HomeTopBar
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.event.FacultyDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.decorator.BottomSheetDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetControllerIcon
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandler



@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CompactModeLayout(
    sheetHandler: BottomSheetHandler,
    facultyListState: FacultyListState,
    onEvent: (FacultyDestinationEvent) -> Unit,
    homeContent: (@Composable () -> Unit)? = null,
) {
    /*
  Note recomposition the faculty destination because recomposition causes animation
                as,we have animation when showing department destination as a result ,to give better user
                            experience not hiding(recompose) the faculties,
                 Use a department background that hide the faculties  without recomposing the faculties
                  */

    Box(modifier = Modifier.fillMaxSize()) {
        BottomSheetDecorator(
            topBar = {
                HomeTopBar(
                    title = "Faculty Info",
                    onNavigationIconClick = {
                        onEvent(FacultyDestinationEvent.ExitRequest)
                    },
                    sheetController = {
                        BottomSheetControllerIcon(
                            handler = sheetHandler
                        )
                    },

                    )
            },
            sheetState = sheetHandler.sheetState.collectAsState().value,
            sheetContent = {
                FacultyList(
                    modifier = Modifier,
                    onEvent = onEvent,
                    state = facultyListState
                )
            },
            content = {
                if (homeContent != null) {
                    homeContent()
                }
            }
        )


    }

}
