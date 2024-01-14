package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetDecorator
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.bottom_sheet.AnimatedBottomSheet
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyInfo
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeDestination(
    faculties: List<FacultyInfo>,
    onFacultySelected: (FacultyInfo) -> Unit,
    onExitRequested: () -> Unit,
    onSearchRequested: () -> Unit,
    content: @Composable () -> Unit,
) {
    val viewModel = remember { HomeViewModel() }
    val sheetState = rememberModalBottomSheetState()
    var sheetVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value

    val hideBottomSheet: () -> Unit = {
        scope.launch {
            sheetVisible = false
            sheetState.hide()
        }
    }
    val onToggleBottomSheet: () -> Unit = {
        scope.launch {
            sheetVisible = if(sheetState.currentValue==SheetValue.Expanded||sheetState.currentValue==SheetValue.PartiallyExpanded){
                hideBottomSheet()
                false
            }
            else{
                sheetState.expand()
                true
            }
        }

    }

    BottomSheetDecorator(
        topBar = {
            HomeTopBar(
                title = "Faculty Info",
                onNavigationIconClick = onExitRequested,
                onToggleBottomSheet = onToggleBottomSheet,
                sheetVisible = sheetVisible,
                onSearchRequest = {
                    //hide the sheet to causes crash
                    hideBottomSheet()
                    onSearchRequested()
                }
            )
        },
        sheetState = sheetState,
        sheetContent = {
            if (sheetState.currentValue!=SheetValue.Hidden){
                AnimatedBottomSheet(
                    visible = sheetVisible,
                    selectedIndex=currentDestinationIndex,
                    faculties=faculties,
                    onFacultyClick = {
                        //hide the sheet to avoid crash
                        hideBottomSheet()
                        viewModel.onSectionSelected(it)
                        onFacultySelected(faculties[it])
                    }
                )
            }
        }, content = content
    )

}


