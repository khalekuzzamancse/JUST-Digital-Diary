package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.decorator.BottomSheetDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetControllerIcon
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandlerImp
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.bottom_sheet.AnimatedBottomSheet
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Department


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeDestination(
    departments: List<Department>,
    onDepartmentSelected: (Department) -> Unit,
    onExitRequest: () -> Unit,
    onSearchRequested: () -> Unit,
    content: @Composable () -> Unit,
) {
    val viewModel = remember { HomeViewModel() }
    val scope = rememberCoroutineScope()
    val sheetHandler =
        remember { BottomSheetHandlerImp(scope = scope, initialState = SheetValue.Expanded) }
    val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value


    BottomSheetDecorator(
        topBar = {
            HomeTopBar(
                title = "Departments Info",
                onNavigationIconClick = onExitRequest,
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
        sheetState = sheetHandler.sheetState.collectAsState().value,
        sheetContent = {
            AnimatedBottomSheet(
                visible = true,
                selectedIndex = currentDestinationIndex,
                faculties = departments,
                onFacultyClick = {
                    //hide the sheet to avoid crash
                    sheetHandler.hideSheet()
                    viewModel.onSectionSelected(it)
                    onDepartmentSelected(departments[it])
                }
            )

        }, content = content
    )

}


