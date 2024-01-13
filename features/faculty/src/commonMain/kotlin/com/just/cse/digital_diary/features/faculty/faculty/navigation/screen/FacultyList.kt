package com.just.cse.digital_diary.features.faculty.faculty.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerState
import com.just.cse.digital_diary.features.faculty.faculty.SearchableEmployeeList
import com.just.cse.digital_diary.features.faculty.faculty.navigation.Faculties
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.generateDummyEmployeeList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/*
Na vhost
 */
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
        val scope = rememberCoroutineScope()
        val drawerController by remember {
            mutableStateOf(ModalDrawerState(scope))
        }


        navigator = LocalNavigator.current
        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
        ModalDrawerDecorator(
            drawerState=drawerController,
            destinations = destinations,
            selectedDesertionIndex = currentDestinationIndex,
            onDestinationSelected = { selectedDestinationIndex ->
                viewModel.onSectionSelected(selectedDestinationIndex)
                val faculty = destinations.getOrNull(selectedDestinationIndex)
                if (faculty != null) {
                    navigator?.push(ListOfDepartments(faculty.key))
                }
            }
        ) {
            SearchableEmployeeList(
               employeeList =  generateDummyEmployeeList(10),
                onNavigationClick = {
                    drawerController.openDrawer()
                }
            )
        }
    }
}

