package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.dept_list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.just.cse.digital_diary.features.common_ui.SearchableEmployeeList
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Department
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

private class NavigationStateHolder {
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }

}

@Composable
internal fun DepartmentList(
    departments: List<Department>,
    onDepartmentSelected:(Department)->Unit,
    onExitRequest: () -> Unit,
) {
    val viewModel = remember {
        NavigationStateHolder()
    }
    val destinations = departments.map {
        NavigationItem(
            key = it.id,
            label = it.fullName,
            unFocusedIcon = it.logo
        )
    }
    val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
//    SearchableEmployeeList(
//        onDrawerOpenRequested = onExitRequest,
//        destinations = destinations,
//        selectedDesertionIndex = currentDestinationIndex,
//        onDestinationSelected = { index ->
//            viewModel.onSectionSelected(index)
//            onDepartmentSelected(departments[index])
//        }
//    )
}

