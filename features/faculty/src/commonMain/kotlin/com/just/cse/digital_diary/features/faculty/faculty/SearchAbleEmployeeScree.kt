package com.just.cse.digital_diary.features.faculty.faculty

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_navigation.BottomNavigationBar
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.modal_drawer.AnimateVisibilityDecorator
import com.just.cse.digital_diary.features.common_ui.search_bar.SearchDecorator
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeCard
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.generateDummyEmployeeList


@Composable
fun SearchableEmployeeList(
    employeeList: List<Employee>,
    onNavigationClick:()->Unit={},
) {
    var selectedBottomDestinations by remember {
        mutableStateOf(0)
    }

    SearchDecorator(
        predicate = { employee, queryText ->
            predicate(employee, queryText)
        },
        items = employeeList,
        navigationIcon = Icons.Default.Menu,
        onNavigationClick = onNavigationClick,
        itemDecorator = { employee, queryText ->
            EmployeeCard(
                modifier = Modifier,
                highlightedText = queryText,
                employee = employee
            )
        },
        content = {modifier->
            when(selectedBottomDestinations){
                0->{
                    AnimateVisibilityDecorator {
                        EmployeeList(
                            modifier = modifier,
                            employees = employeeList,
                        )
                    }
                }
                else->{
                    AnimateVisibilityDecorator {
                        ScreenUnderConstruction(modifier)
                    }

                }
            }

        },
        bottomNavbar = {
            BottomNavigationBar(
                destinations = bottomDestinations,
                selectedDestinationIndex = selectedBottomDestinations,
                onDestinationSelected = {
                    selectedBottomDestinations = it
                }
            )
        }

    )

}


fun predicate(employee: Employee, queryText: String): Boolean {
    return (
            employee.name.contains(queryText, ignoreCase = true)
                    || employee.deptName.contains(queryText, ignoreCase = true)
                    || employee.deptSortName.contains(queryText, ignoreCase = true)
                    || employee.email.contains(queryText, ignoreCase = true)
            )
}


val bottomDestinations = listOf(
    NavigationItem(
        key = 0,
        label = "Employees",
        unFocusedIcon = Icons.Outlined.Person,
        focusedIcon = Icons.Filled.Person
    ),
    NavigationItem(
        key = 1,
        label = "UnderConstruction",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work
    ),
    NavigationItem(
        key = 2,
        label = "UnderConstruction",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work
    ),
)

@Composable
fun ScreenUnderConstruction(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text("Under construction")
    }
}