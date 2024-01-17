package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.decorator.BottomSheetDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetControllerIcon
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandlerImp
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.bottom_sheet.AnimatedBottomSheet
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandler
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.FacultyInfo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CompactScreenSlots(

    faculties: List<FacultyInfo>,
    onFacultySelected: (FacultyInfo) -> Unit,
    onExitRequested: () -> Unit,
    onSearchRequested: () -> Unit,
    content: @Composable () -> Unit,
) {
    val viewModel = remember { HomeViewModel() }
    val scope = rememberCoroutineScope()
    val sheetHandler = remember { BottomSheetHandlerImp(scope=scope, initialState = SheetValue.Expanded) }
    val sheetState = sheetHandler.sheetState.collectAsState().value
    val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value


    BottomSheetDecorator(
        topBar = {
            HomeTopBar(
                title = "Faculty Info",
                onNavigationIconClick = onExitRequested,
                sheetController = {
                    BottomSheetControllerIcon(
                        handler = sheetHandler
                    )
                },
                onSearchRequest = {
                    //hide the sheet to causes crash
                    sheetHandler.hideSheet()
                    onSearchRequested()
                }
            )
        },
        sheetState = sheetState,
        sheetContent = {
            AnimatedBottomSheet(
                visible = true,
                selectedIndex = currentDestinationIndex,
                faculties = faculties,
                onFacultyClick = {
                    //hide the sheet to avoid crash
                    sheetHandler.hideSheet()
                    viewModel.onSectionSelected(it)
                    onFacultySelected(faculties[it])
                }
            )
        },
        content = content
    )

}


