package com.just.cse.digital_diary.features.faculty.faculty.navigation.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.ModalDrawerDecorator
import com.just.cse.digital_diary.features.faculty.faculty.SearchDecorator
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
        navigator = LocalNavigator.current
        val viewModel = remember { TopMostDestinationViewModel() }
        val currentDestinationIndex = viewModel.selectedSectionIndex.collectAsState().value
        ModalDrawerDecorator(
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
            //  SearchScreenTopBar()
//            SearchDecorator(
//                navigationIcon = Icons.Default.Menu,
//                onNavigationClick = {
//
//                },
//                employees = employeeList,
//                contentOnNoSearch = {
//                    EmployeeList(
//                        modifier = it,
//                        employees = employeeList,
//                    )
//                }
//            )
            SearchDecorator(
                predicate={employee,queryText->
                    predicate(employee,queryText)
                },
                items = employeeList,
                navigationIcon = Icons.Default.Menu,
                onNavigationClick = {},
                itemDecorator = {employee,queryText->
                    EmployeeCard(
                        modifier = Modifier,
                        highlightedText =queryText,
                        employee= employee
                    )
                },
                contentOnNoSearch = {
                    EmployeeList(
                        modifier = it,
                        employees = employeeList,
                    )
                },
            )
        }
    }
}


val employeeList = generateDummyEmployeeList(10)

fun predicate(employee: Employee, queryText: String): Boolean {
    return (
            employee.name.contains(queryText, ignoreCase = true)
                    || employee.deptName.contains(queryText, ignoreCase = true)
                    || employee.deptSortName.contains(queryText, ignoreCase = true)
                    || employee.email.contains(queryText, ignoreCase = true)
            )
}