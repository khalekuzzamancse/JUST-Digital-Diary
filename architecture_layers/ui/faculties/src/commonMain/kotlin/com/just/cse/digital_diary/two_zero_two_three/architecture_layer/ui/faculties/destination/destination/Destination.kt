package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.destination

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.faculties.destination.viewmodel.FacultyDestinationViewModel
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandlerImp

@Composable
fun FacultiesDestination(
    viewModel: FacultyDestinationViewModel,
    homeContent: (@Composable () -> Unit)? = null,
) {
    CompactModeLayout(
        sheetHandler = remember { BottomSheetHandlerImp() },
        facultyListState = viewModel.state.collectAsState().value.facultyListState,
        onEvent = viewModel::onEvent,
        homeContent=homeContent
    )

}