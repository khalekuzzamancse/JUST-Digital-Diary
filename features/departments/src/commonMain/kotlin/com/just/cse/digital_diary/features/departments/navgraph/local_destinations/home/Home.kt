package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home

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
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetNavigation
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.home.bottom_sheet.AnimatedBottomSheet
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Department
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeDestination(
    departments: List<Department>,
    onDepartmentSelected:(Department)->Unit,
    onExitRequest: () -> Unit,
    onSearchRequested: () -> Unit,
    content:@Composable ()->Unit,
) {
    val viewModel = remember { HomeViewModel() }
    val sheetState = rememberModalBottomSheetState()
    var sheetVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
    val destinations = departments.map {
        NavigationItem(
            label = it.fullName,
            unFocusedIcon = it.logo,
            key = it.id
        )
    }
    val onToggleBottomSheet: () -> Unit = {
        sheetVisible=!sheetVisible
        scope.launch {
            if (sheetState.isVisible)
                sheetState.hide()
            else sheetState.expand()
        }
    }
    val hideBottomSheet: () -> Unit = {

        sheetVisible=!sheetVisible
        scope.launch {
            sheetState.hide()
        }
    }

    BottomSheetDecorator(
        topBar = {
            HomeTopBar(
                title = "Departments Info",
                onNavigationIconClick = onExitRequest,
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
            if (sheetState.currentValue!= SheetValue.Hidden){
                AnimatedBottomSheet(
                    visible = sheetVisible,
                    selectedIndex=currentDestinationIndex,
                    faculties=departments,
                    onFacultyClick = {
                        //hide the sheet to avoid crash
                        hideBottomSheet()
                        viewModel.onSectionSelected(it)
                        onDepartmentSelected(departments[it])
                    }
                )
            }
        }, content = content
    )

}


