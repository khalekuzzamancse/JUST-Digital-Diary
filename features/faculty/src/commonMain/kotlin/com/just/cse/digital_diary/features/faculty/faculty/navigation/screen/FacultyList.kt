package com.just.cse.digital_diary.features.faculty.faculty.navigation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.common_ui.bottom_navigation.BottomNavigationBar
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerState
import com.just.cse.digital_diary.features.common_ui.search_bar.SearchDecorator
import com.just.cse.digital_diary.features.faculty.faculty.SearchableEmployeeList
import com.just.cse.digital_diary.features.faculty.faculty.navigation.Faculties
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeCard
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee
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
            drawerController=drawerController,
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

