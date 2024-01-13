package com.just.cse.digital_diary.features.faculty.faculty.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.faculty.faculty.SearchableEmployeeList
import com.just.cse.digital_diary.features.faculty.faculty.navigation.Faculties
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class TopMostDestinationViewModel {
    private val _selectedIndex = MutableStateFlow(-1)
    val selectedSectionIndex = _selectedIndex.asStateFlow()
    fun onSectionSelected(index: Int) {
        _selectedIndex.value = index
    }

}

val destinations = Faculties.destinations


class FacultyModuleNavHost : Screen {
    private var navigator: Navigator? = null
    @Composable
    override fun Content() {
        navigator = LocalNavigator.current
        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
        SearchableEmployeeList(
            destinations = destinations,
            selectedDesertionIndex = currentDestinationIndex,
            onDestinationSelected = { selectedDestinationIndex ->
                viewModel.onSectionSelected(selectedDestinationIndex)
                val faculty = destinations.getOrNull(selectedDestinationIndex)
                if (faculty != null) {
                    navigator?.push(ListOfDepartments(faculty.key))
                }
            },
        )
    }
}

