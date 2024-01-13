package com.just.cse.digital_diary.features.faculty.faculty.navigation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerState
import com.just.cse.digital_diary.features.faculty.faculty.SearchableEmployeeList
import com.just.cse.digital_diary.two_zero_two_three.department.navigation.DepartmentModule
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyRepository
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.generateDummyEmployeeList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationStateHolder {
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }

}


class ListOfDepartments(
    private val facultyId: String,
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = remember {
            NavigationStateHolder()
        }
        val departments = FacultyRepository.getDepartments(facultyId)
        val destinations = departments.map {
            NavigationItem(
                key = it.id,
                label = it.fullName,
                unFocusedIcon = it.logo
            )
        }
        val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
            SearchableEmployeeList(
                destinations = destinations,
                selectedDesertionIndex = currentDestinationIndex,
                onDestinationSelected = { index ->
                    viewModel.onSectionSelected(index)
                    val deptId = destinations.getOrNull(index)?.key
                    if (deptId != null) {
                        navigator?.push(DepartmentModule(deptId))
                    }
                }
            )

    }

}

